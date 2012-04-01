package net.gageot.codestory;

import net.gageot.testing.*;
import org.junit.*;

import static com.jayway.restassured.RestAssured.*;
import static junit.framework.Assert.*;
import static net.gageot.testing.ServiceRule.*;
import static org.hamcrest.Matchers.*;

public class CodeStoryServerTest {
	@ClassRule
	public static ServiceRule<CodeStoryServer> server = start(CodeStoryServer.class);

	@Test
	public void should_retrieve_commits_as_json() {
		given().port(port()).
				expect().body("committer", hasItems("dgageot", "jeanlaurent")).
				when().get("/commits.json");
	}

	@Test
	public void should_display_index() throws Exception {
		if (0 != new Shell().execute("/usr/local/bin/node testIndex.js %d", port())) {
			fail("ERROR");
		}
	}

	static int port() {
		return server.service().port();
	}
}
