package net.gageot;

import com.google.inject.*;
import net.gageot.codestory.*;
import net.gageot.util.proxy.*;
import org.lesscss.*;

import javax.ws.rs.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

@Path("/")
@Singleton
public class CodeStoryResource {
	private final AllCommits allCommits;

	@Inject
	public CodeStoryResource(AllCommits allCommits) {
		this.allCommits = CacheProxy.wrap(allCommits, 1, TimeUnit.MINUTES, 100);
	}

	@GET
	@Produces("text/html;charset=UTF-8")
	public File index() {
		return staticResource("index.html");
	}

	@GET
	@Path("{path : .*\\.js}")
	@Produces("text/javascript;charset=UTF-8")
	public File js(@PathParam("path") String path) {
		return staticResource(path);
	}

	@GET
	@Path("{path : .*\\.png}")
	@Produces("image/png")
	public File png(@PathParam("path") String path) {
		return staticResource(path);
	}

	@GET
	@Path("{path : .*\\.jpg}")
	@Produces("image/jpeg")
	public File jpeg(@PathParam("path") String path) {
		return staticResource(path);
	}

	@GET
	@Path("{path : .*\\.css}")
	public File css(@PathParam("path") String path) {
		return staticResource(path);
	}

	@GET
	@Path("{path : .*\\.less}")
	public String less(@PathParam("path") String path) throws IOException, LessException {
		return new LessCompiler().compile(staticResource(path));
	}

	@GET
	@Path("commits.json")
	@Produces("application/json;charset=UTF-8")
	public List<Commit> commits() {
		return allCommits.list("dgageot", "sonar");
	}

	private static File staticResource(String path) {
		return new File("web", path);
	}
}
