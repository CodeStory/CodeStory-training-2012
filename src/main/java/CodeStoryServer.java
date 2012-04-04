import com.sun.jersey.api.container.httpserver.*;
import com.sun.net.httpserver.*;

import javax.ws.rs.*;
import java.io.*;

@Path("/")
public class CodeStoryServer {
	@GET
	public File homepage() {
		return new File("index.html");
	}

	@GET
	@Path("mustache.js")
	public File mustache() {
		return new File("mustache.js");
	}

	@GET
	@Path("jquery.js")
	public File jquery() {
		return new File("jquery.js");
	}

	public static void main(String[] args) throws Exception {
		HttpServer httpServer = HttpServerFactory.create("http://localhost:8080/");
		httpServer.start();
	}
}
