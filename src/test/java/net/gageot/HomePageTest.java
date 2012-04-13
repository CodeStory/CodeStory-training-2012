package net.gageot;

import net.gageot.codestory.*;
import net.gageot.test.rules.*;
import net.gageot.test.utils.*;
import org.junit.*;

import java.util.*;

import static net.gageot.test.rules.ServiceRule.*;
import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

public class HomePageTest {
	private CommitService commitService = mock(CommitService.class);

	@ClassRule
	public static ServiceRule<CodeStoryServer> server = startWithRandomPort(CodeStoryServer.class);

	@Test
	public void should_show_homepage() {
		when(commitService.listCommits()).thenReturn(Arrays.asList( //
				new Commit("dgageot", "03/01/2012", "Troisieme commit", "https://secure.gravatar.com/avatar/f0887bf6175ba40dca795eb37883a8cf"), //
				new Commit("jeanlaurent", "02/01/2012", "Deuxieme commit", "https://secure.gravatar.com/avatar/649d3668d3ba68e75a3441dec9eac26e"), //
				new Commit("eric", "01/01/2012", "Premier commit", "https://secure.gravatar.com/avatar/77da98419ae312eb0e322a3dac44a734")));

		int exitCode = runZombieJsTest("HomePageTest.js");

		assertThat(exitCode).isZero();
	}

	private static int runZombieJsTest(String jsFile) {
		return new Shell().execute("/usr/local/bin/node src/test/resources/%s %d", jsFile, server.service().getPort());
	}
}
