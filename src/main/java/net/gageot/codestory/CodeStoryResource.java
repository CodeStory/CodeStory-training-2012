package net.gageot.codestory;

import javax.ws.rs.*;
import java.io.*;
import java.util.*;

@Path("/")
public class CodeStoryResource {
	@GET
	@Path("commits.json")
	@Produces("application/json")
	public List<Commit> commits() {
		return new CodeStory().commits("dgageot", "NodeGravatar");
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
}
