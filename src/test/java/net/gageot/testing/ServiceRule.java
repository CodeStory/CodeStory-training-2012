package net.gageot.testing;

import com.google.common.util.concurrent.*;
import org.junit.rules.*;

import java.lang.reflect.*;
import java.util.*;

public class ServiceRule<T extends Service> extends ExternalResource {
	private static final int TRY_COUNT = 10;
	private static final int DEFAULT_PORT = 8183;

	private final Random random;
	private T service;

	private ServiceRule() {
		random = new Random();
	}

	public static <T extends Service> ServiceRule<T> create() {
		return new ServiceRule<T>();
	}

	@Override
	protected void before() {
		for (int i = 0; i < TRY_COUNT; i++) {
			try {
				service = createServive(getRandomPort());
				service.startAndWait();
				return;
			} catch (Exception e) {
				System.err.println("Unable to bind server: " + e);
			}
		}
		throw new IllegalStateException("Unable to start server");
	}

	@Override
	protected void after() {
		if (service != null) {
			service.stopAndWait();
		}
	}

	@SuppressWarnings("unchecked")
	private T createServive(int port) throws Exception {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		Class<T> serverClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];

		return serverClass.getDeclaredConstructor(int.class).newInstance(port);
	}

	public T service() {
		return service;
	}

	private synchronized int getRandomPort() {
		return DEFAULT_PORT + random.nextInt(1000);
	}
}
