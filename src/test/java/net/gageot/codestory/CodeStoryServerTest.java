package net.gageot.codestory;

import org.apache.commons.exec.*;
import org.junit.*;

import static com.jayway.restassured.RestAssured.*;
import static junit.framework.Assert.*;
import static org.hamcrest.Matchers.*;

public class CodeStoryServerTest {
	@ClassRule
	public static HttpServerRule server = new HttpServerRule();

	@Test
	public void should_retrieve_commits_as_json() {
		given().port(server.port()).
				expect().body("committer", hasItems("dgageot", "jeanlaurent")).
				when().get("/commits.json");
	}

	@Test
	public void should_display_index() throws Exception {
		if (0 != new DefaultExecutor().execute(CommandLine.parse("/usr/local/bin/node testIndex.js " + server.port()))) {
			fail("ERROR");
		}
	}
}
