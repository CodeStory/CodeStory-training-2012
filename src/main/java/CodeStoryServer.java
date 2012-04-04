import com.sun.jersey.api.container.httpserver.*;
import com.sun.net.httpserver.*;

import javax.ws.rs.*;

@Path("/")
public class CodeStoryServer {
	@GET
	public String homepage() {
		return "<html><head><title>CodeStory - Homepage</title></head></html>";
	}

	public static void main(String[] args) throws Exception {
		HttpServer httpServer = HttpServerFactory.create("http://localhost:8080/");
		httpServer.start();
	}
}
