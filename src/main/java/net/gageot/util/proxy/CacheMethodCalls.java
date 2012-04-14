package net.gageot.util.proxy;

import com.google.common.base.*;
import com.google.common.cache.*;
import com.google.common.util.concurrent.*;
import lombok.*;
import org.aopalliance.intercept.*;

import java.lang.reflect.*;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.*;

public class CacheMethodCalls implements MethodInterceptor {
	private final LoadingCache<Invocation, Object> cache;

	public CacheMethodCalls() {
		cache = CacheBuilder.newBuilder() //
				.refreshAfterWrite(1, MINUTES) //
				.build(new CacheLoader<Invocation, Object>() {
					@Override
					public Object load(Invocation invocation) {
						try {
							return invocation.getMethodInvocation().proceed();
						} catch (Throwable e) {
							throw Throwables.propagate(e);
						}
					}

					@Override
					public ListenableFuture<Object> reload(final Invocation invocation, Object oldValue) {
						return ListenableFutureTask.create(new Callable<Object>() {
							@Override
							public Object call() {
								return load(invocation);
							}
						});
					}
				});
	}

	@Override
	public Object invoke(MethodInvocation invocation) {
		return cache.getUnchecked(new Invocation(invocation, invocation.getMethod(), invocation.getArguments()));
	}

	@Data
	static class Invocation {
		private final transient MethodInvocation methodInvocation;
		private final Method method;
		private final Object[] arguments;
	}
}
