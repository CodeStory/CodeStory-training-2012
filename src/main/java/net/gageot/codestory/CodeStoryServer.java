package net.gageot.codestory;

import com.google.common.util.concurrent.*;
import com.sun.jersey.api.container.httpserver.*;
import com.sun.jersey.api.core.*;
import com.sun.net.httpserver.*;
import org.codehaus.jackson.jaxrs.*;

import java.io.*;

import static java.lang.String.*;

public class CodeStoryServer extends AbstractIdleService {
	private static final int DEFAULT_PORT = 8080;

	private final int port;
	private HttpServer httpServer;

	public CodeStoryServer() {
		this(DEFAULT_PORT);
	}

	public CodeStoryServer(int port) {
		this.port = port;
	}

	public int port() {
		return port;
	}

	@Override
	protected void startUp() throws IOException {
		ResourceConfig config = new DefaultResourceConfig(JacksonJsonProvider.class, CodeStoryResource.class);

		httpServer = HttpServerFactory.create(format("http://localhost:%d/", port), config);
		httpServer.start();
	}

	@Override
	protected void shutDown() {
		httpServer.stop(1);
	}
}
