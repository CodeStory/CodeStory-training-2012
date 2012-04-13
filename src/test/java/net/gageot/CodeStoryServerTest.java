package net.gageot;

import com.google.common.util.concurrent.*;
import org.junit.*;

public class CodeStoryServerTest {

	@Test
	public void server_should_startup() throws Exception {
		CodeStoryServer codeStoryServer = new CodeStoryServer(8080);
		Service.State state = codeStoryServer.startAndWait();
	}

}
