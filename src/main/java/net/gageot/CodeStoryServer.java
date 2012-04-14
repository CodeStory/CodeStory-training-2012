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

import static com.google.inject.Guice.*;
import static com.google.inject.matcher.Matchers.*;
import static com.google.inject.name.Names.*;
import static com.google.inject.util.Modules.*;

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

		httpServer = HttpServerFactory.create("http://localhost:" + port + "/", config, ioc);
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
		@Override
		protected void configure() {
			bindConstant().annotatedWith(named("username")).to("dgageot");
			bindConstant().annotatedWith(named("project")).to("sonar");
			bindInterceptor(any(), annotatedWith(Cached.class), new CacheMethodCalls());
		}
	}
}
