import com.sun.jersey.api.container.httpserver.*;
import com.sun.net.httpserver.*;
import net.gageot.codestory.*;

import javax.ws.rs.*;
import java.io.*;
import java.util.*;

import static java.util.Arrays.*;

@Path("/")
public class CodeStoryServer {
	@GET
	public File homepage() {
		return new File("index.html");
	}

	@GET
	@Path("commits")
	@Produces("application/json;charset=UTF-8")
	public List<Commit> commits() {
		return asList( //
				new Commit("dgageot", "02/12/2012", "Troisième commit", "https://secure.gravatar.com/avatar/f0887bf6175ba40dca795eb37883a8cf"), //
				new Commit("jeanlaurent", "01/12/2012", "Deuxième commit", "https://secure.gravatar.com/avatar/649d3668d3ba68e75a3441dec9eac26e"), //
				new Commit("eric", "29/11/2012", "Premier commit", "https://secure.gravatar.com/avatar/77da98419ae312eb0e322a3dac44a734") //
		);
	}

	@GET
	@Path("{path: .*\\.js}")
	@Produces("text/javascript")
	public File js(@PathParam("path") String path) {
		return new File(path);
	}

	public static void main(String[] args) throws Exception {
		HttpServer httpServer = HttpServerFactory.create("http://localhost:8080/");
		httpServer.start();
	}
}
