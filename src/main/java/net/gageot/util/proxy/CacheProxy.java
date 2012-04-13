package net.gageot.util.proxy;

import com.google.common.base.*;
import com.google.common.cache.*;
import com.google.common.util.concurrent.*;
import lombok.*;
import net.sf.cglib.proxy.*;

import java.lang.reflect.*;
import java.util.concurrent.*;

public final class CacheProxy<T> implements MethodInterceptor {
	protected static final Object NULL = new Object();

	private final LoadingCache<Invocation, Object> cache;

	private CacheProxy(final T target, long refresh, TimeUnit refreshTimeUnit, int maximumSize) {
		cache = CacheBuilder.newBuilder() //
				.maximumSize(maximumSize) //
				.refreshAfterWrite(refresh, refreshTimeUnit) //
				.build(new CacheLoader<Invocation, Object>() {
					@Override
					public Object load(Invocation invocation) throws Exception {
						Object methodResult = invocation.getMethod().invoke(target, invocation.getArguments());
						return null == methodResult ? NULL : methodResult;
					}

					@Override
					public ListenableFuture<Object> reload(final Invocation invocation, final Object oldValue) throws Exception {
						return ListenableFutureTask.create(new Callable<Object>() {
							@Override
							public Object call() throws Exception {
								return load(invocation);
							}
						});
					}
				});
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		try {
			Object result = cache.get(new Invocation(method, args));
			return NULL == result ? null : result;
		} catch (Exception e) {
			throw Throwables.getRootCause(e);
		}
	}

	public static <T> T wrap(T target, long expiry, TimeUnit expiryTimeUnit, int maximumSize) {
		return ProxyHelper.wrap(target, new CacheProxy<T>(target, expiry, expiryTimeUnit, maximumSize));
	}

	@Data
	static class Invocation {
		private final Method method;
		private final Object[] arguments;
	}
}
