package net.gageot.util.proxy;

import com.google.common.collect.*;
import net.sf.cglib.proxy.*;
import org.objenesis.*;

import java.lang.reflect.*;
import java.util.*;

public final class ProxyHelper {
	private static final ObjenesisStd OBJENESIS = new ObjenesisStd();

	private ProxyHelper() {
		// Static class
	}

	@SuppressWarnings("unchecked")
	public static <T> T wrap(T target, MethodInterceptor interceptor, Class<?>... moreInterfaces) {
		return (T) wrap(target.getClass(), interceptor, moreInterfaces);
	}

	@SuppressWarnings("unchecked")
	public static <T> T wrap(Class<T> targetClass, MethodInterceptor interceptor, Class<?>... moreInterfaces) {
		Set<Class<?>> interfaces = Sets.newHashSet();

		Class<?> proxySuperClass = targetClass;
		if (isCglibProxyClass(targetClass)) {
			proxySuperClass = targetClass.getSuperclass();
			interfaces.addAll(Arrays.asList(targetClass.getInterfaces()));
		}
		interfaces.addAll(Arrays.asList(moreInterfaces));

		try {
			setConstructorsAccessible(proxySuperClass, true);

			Enhancer enhancer = new Enhancer() {
				@Override
				@SuppressWarnings("rawtypes")
				protected void filterConstructors(Class sc, List constructors) {
					// Don't filter
				}
			};
			enhancer.setClassLoader(proxySuperClass.getClassLoader());
			enhancer.setUseFactory(true);
			enhancer.setSuperclass(proxySuperClass);
			enhancer.setInterfaces(interfaces.toArray(new Class[interfaces.size()]));
			enhancer.setCallbackTypes(new Class[] { MethodInterceptor.class, NoOp.class });
			enhancer.setCallbackFilter(IGNORE_BRIDGE_METHODS);

			Class<?> proxyClass = enhancer.createClass();
			return (T) proxySuperClass.cast(createProxy(proxyClass, interceptor));
		} finally {
			setConstructorsAccessible(proxySuperClass, false);
		}
	}

	private static final CallbackFilter IGNORE_BRIDGE_METHODS = new CallbackFilter() {
		@Override
		public int accept(Method method) {
			return method.isBridge() ? 1 : 0;
		}
	};

	private static Object createProxy(Class<?> proxyClass, MethodInterceptor interceptor) {
		Factory proxy = (Factory) OBJENESIS.newInstance(proxyClass);
		proxy.setCallbacks(new Callback[] { interceptor, NoOp.INSTANCE });
		return proxy;
	}

	private static void setConstructorsAccessible(Class<?> mockedType, boolean accessible) {
		for (Constructor<?> constructor : mockedType.getDeclaredConstructors()) {
			constructor.setAccessible(accessible);
		}
	}

	private static boolean isCglibProxyClass(Class<?> clazz) {
		return (clazz != null) && clazz.getName().contains("$$");
	}
}
