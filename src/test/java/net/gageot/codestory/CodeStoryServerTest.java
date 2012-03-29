package net.gageot.codestory;

import com.sun.net.httpserver.*;
import org.junit.*;

import java.io.*;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CodeStoryServerTest {
	private HttpServer server;

	@Before
	public void startWebServer() throws IOException {
		server = CodeStoryServer.start();
	}

	@After
	public void stopWebServer() {
		server.stop(1);
	}

	@Test
	public void should_retrieve_commits_as_JSON() {
		expect().body("committer", hasItems("dgageot", "jlm")).when().get("/commits.json");
	}
}
