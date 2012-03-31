package net.gageot.codestory;

import com.google.common.util.concurrent.*;
import com.sun.jersey.api.container.httpserver.*;
import com.sun.jersey.api.core.*;
import com.sun.net.httpserver.*;
import org.codehaus.jackson.jaxrs.*;

import javax.ws.rs.*;
import java.io.*;
import java.util.*;

import static java.lang.String.*;

@Path("/")
public class CodeStoryServer extends AbstractIdleService {
	private static final int DEFAULT_PORT = 8080;

	private final int port;
	private HttpServer httpServer;

	public CodeStoryServer(int port) {
		this.port = port;
	}

	public CodeStoryServer() {
		this(DEFAULT_PORT);
	}

	public int getPort() {
		return port;
	}

	@GET
	@Path("commits.json")
	@Produces("application/json")
	public List<Commit> commits() {
		return new CodeStory().getCommitsFrom("dgageot", "NodeGravatar");
	}

	@GET
	@Path("index.html")
	public File index() {
		return new File("web", "index.html");
	}

	@GET
	@Path("{path: .*\\.js}")
	public File javascript(@PathParam("path") String path) {
		return new File("web", path);
	}

	@Override
	protected void startUp() throws IOException {
		ResourceConfig config = new DefaultResourceConfig(JacksonJsonProvider.class, CodeStoryServer.class);

		httpServer = HttpServerFactory.create(format("http://localhost:%d/", port), config);
		httpServer.start();
	}

	@Override
	protected void shutDown() {
		httpServer.stop(1);
	}
}
