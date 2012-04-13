package net.gageot;

import org.junit.*;

import java.util.*;

public class CodeStoryServerTest {
	@Test
	public void server_should_start() {
		CodeStoryServer server = new CodeStoryServer(randomPort());
		server.startAndWait();
		server.stopAndWait();
	}

	static int randomPort() {
		return 8080 + new Random().nextInt(100);
	}
}
