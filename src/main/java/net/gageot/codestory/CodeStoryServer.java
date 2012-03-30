package net.gageot.codestory;

import com.sun.jersey.api.container.httpserver.*;
import com.sun.jersey.api.core.*;
import com.sun.net.httpserver.*;
import org.codehaus.jackson.jaxrs.*;

import javax.ws.rs.*;
import java.io.*;
import java.util.*;

@Path("/")
public class CodeStoryServer {
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

	public static HttpServer start(int port) throws IOException {
		ResourceConfig config = new DefaultResourceConfig(JacksonJsonProvider.class, CodeStoryServer.class);

		HttpServer httpServer = HttpServerFactory.create("http://localhost:" + port + "/", config);
		httpServer.start();

		return httpServer;
	}
}
