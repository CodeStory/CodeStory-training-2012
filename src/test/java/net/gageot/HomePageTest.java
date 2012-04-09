package net.gageot;

import net.gageot.test.rules.*;
import net.gageot.test.utils.*;
import org.junit.*;

import static net.gageot.test.rules.ServiceRule.*;
import static org.fest.assertions.Assertions.*;

public class HomePageTest {
	@ClassRule
	public static ServiceRule<CodeStoryServer> server = startWithRandomPort(CodeStoryServer.class);

	@Test
	public void should_show_homepage() {
		int exitCode = runZombieJsTest("HomePageTest.js");

		assertThat(exitCode).isZero();
	}

	private static int runZombieJsTest(String jsFile) {
		return new Shell().execute("/usr/local/bin/node src/test/resources/%s %d", jsFile, server.service().getPort());
	}
}
