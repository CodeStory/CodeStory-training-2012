package net.gageot;

import com.google.common.util.concurrent.*;
import com.google.inject.*;
import com.sun.jersey.api.container.httpserver.*;
import com.sun.jersey.api.core.*;
import com.sun.jersey.core.spi.component.ioc.*;
import com.sun.jersey.guice.spi.container.*;
import com.sun.net.httpserver.*;
import net.gageot.util.proxy.*;
import org.codehaus.jackson.jaxrs.*;

import java.io.*;
import java.net.*;

import static com.google.inject.Guice.*;
import static com.google.inject.util.Modules.*;
import static java.util.concurrent.TimeUnit.*;

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

	@Override
	protected void startUp() throws IOException {
		ResourceConfig config = new DefaultResourceConfig(CodeStoryResource.class, JacksonJsonProvider.class);

		Injector injector = createInjector(override(new CodeStoryServerModule()).with(modules));
		IoCComponentProviderFactory ioc = new GuiceComponentProviderFactory(config, injector);

		URI uri = URI.create("http://localhost:" + port + "/");

		httpServer = HttpServerFactory.create(uri, config, ioc);
		httpServer.start();
	}

	@Override
	protected void shutDown() {
		httpServer.stop(1);
	}

	public static void main(String[] args) {
		new CodeStoryServer(8080).startAndWait();
	}

	static class CodeStoryServerModule extends AbstractModule {
		protected void configure() {
			bind(AllCommits.class).toInstance(CacheProxy.wrap(new AllCommits("dgageot", "NodeGravatar"), 1, MINUTES, 100));
		}
	}
}
