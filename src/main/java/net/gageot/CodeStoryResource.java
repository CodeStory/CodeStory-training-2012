package net.gageot;

import net.gageot.codestory.*;
import org.lesscss.*;

import javax.ws.rs.*;
import java.io.*;
import java.util.*;

import static java.util.Arrays.*;

@Path("/")
public class CodeStoryResource {
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
		return asList( //
				new Commit("dgageot", "03/01/2012", "Troisieme commit", "https://secure.gravatar.com/avatar/f0887bf6175ba40dca795eb37883a8cf"), //
				new Commit("jeanlaurent", "02/01/2012", "Deuxieme commit", "https://secure.gravatar.com/avatar/649d3668d3ba68e75a3441dec9eac26e"), //
				new Commit("eric", "01/01/2012", "Premier commit", "https://secure.gravatar.com/avatar/77da98419ae312eb0e322a3dac44a734"));
	}

	private static File staticResource(String path) {
		return new File("web", path);
	}
}
