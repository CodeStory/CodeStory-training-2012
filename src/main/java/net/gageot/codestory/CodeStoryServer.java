package net.gageot.codestory;

import com.sun.jersey.api.container.httpserver.*;
import com.sun.net.httpserver.*;

import javax.ws.rs.*;
import java.io.*;
import java.util.*;

@Path("/")
public class CodeStoryServer {
	@GET
	@Path("commits.json")
	@Produces("application/json")
	public List<Commit> commits() {
		return new CodeStory().getCommitsFrom("");
	}

	public static HttpServer start() throws IOException {
		HttpServer httpServer = HttpServerFactory.create("http://localhost:8080/");
		httpServer.start();
		return httpServer;
	}
}
