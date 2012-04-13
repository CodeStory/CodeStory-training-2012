package net.gageot;

import com.google.inject.*;
import net.gageot.codestory.*;
import org.lesscss.*;

import javax.activation.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.*;
import java.net.*;
import java.util.*;

@Path("/")
@Singleton
public class CodeStoryResource {
	@Inject AllCommits allCommits;

	@GET
	public Response index() {
		return Response.temporaryRedirect(URI.create("index.html")).build();
	}

	@GET
	@Path("commits.json")
	@Produces("application/json;charset=UTF-8")
	public List<Commit> commits() {
		return allCommits.list();
	}

	@GET
	@Path("{path : .*\\.less}")
	public String less(@PathParam("path") String path) throws IOException, LessException {
		return new LessCompiler().compile(new File("web", path));
	}

	@GET
	@Path("{path : .*}")
	public Response staticResource(@PathParam("path") String path) {
		File file = new File("web", path);
		if (!file.exists()) {
			throw new WebApplicationException(404);
		}
		String mimeType = new MimetypesFileTypeMap().getContentType(file);
		return Response.ok(file, mimeType).build();
	}
}
