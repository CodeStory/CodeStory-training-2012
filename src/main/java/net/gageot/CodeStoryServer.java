package net.gageot;

import com.google.common.util.concurrent.*;
import com.sun.jersey.api.container.httpserver.*;
import com.sun.jersey.api.core.*;
import com.sun.net.httpserver.*;
import org.codehaus.jackson.jaxrs.*;

import java.io.*;
import java.net.*;

public class CodeStoryServer extends AbstractIdleService {
	private final int port;
	private HttpServer httpServer;

	public CodeStoryServer(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public static void main(String[] args) {
		new CodeStoryServer(8080).startAndWait();
	}

	@Override
	protected void startUp() throws IOException {
		DefaultResourceConfig config = new DefaultResourceConfig(CodeStoryResource.class, JacksonJsonProvider.class);
		URI uri = URI.create("http://localhost:" + port + "/");

		httpServer = HttpServerFactory.create(uri, config);
		httpServer.start();
	}

	@Override
	protected void shutDown() {
		httpServer.stop(1);
	}
}
