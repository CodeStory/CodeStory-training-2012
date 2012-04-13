package net.gageot;

import com.google.common.util.concurrent.*;
import com.google.inject.*;
import com.sun.jersey.api.container.httpserver.*;
import com.sun.jersey.api.core.*;
import com.sun.jersey.guice.spi.container.*;
import com.sun.net.httpserver.*;
import org.codehaus.jackson.jaxrs.*;

import java.io.*;
import java.net.*;

public class CodeStoryServer extends AbstractIdleService {
	private final int port;
	private Module[] modules;
	private HttpServer httpServer;

	public CodeStoryServer(int port, Module... modules) {
		this.port = port;
		this.modules = modules;
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
		Injector injector = Guice.createInjector(modules);
		GuiceComponentProviderFactory ioc = new GuiceComponentProviderFactory(config, injector);

		URI uri = URI.create("http://localhost:" + port + "/");

		httpServer = HttpServerFactory.create(uri, config, ioc);
		httpServer.start();
	}

	@Override
	protected void shutDown() {
		httpServer.stop(1);
	}
}
