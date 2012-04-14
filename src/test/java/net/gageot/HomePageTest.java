package net.gageot;

import com.google.inject.*;
import net.gageot.codestory.*;
import net.gageot.test.rules.*;
import net.gageot.test.utils.*;
import org.junit.*;

import java.util.*;

import static java.util.Arrays.*;
import static net.gageot.test.rules.ServiceRule.*;
import static org.fest.assertions.Assertions.*;
import static org.mockito.BDDMockito.*;

public class HomePageTest {
	static AllCommits allCommits = mock(AllCommits.class);

	@ClassRule
	public static ServiceRule<CodeStoryServer> server = startWithRandomPort(CodeStoryServer.class, new AbstractModule() {
		@Override
		protected void configure() {
			bind(AllCommits.class).toInstance(allCommits);
		}
	});

	@Test
	public void should_show_homepage() {
		List<Commit> commits = asList( //
				commit("dgageot", "03/01/2012", "Quatrieme commit", "f0887bf6175ba40dca795eb37883a8cf"), //
				commit("jeanlaurent", "03/01/2012", "Troisieme commit", "649d3668d3ba68e75a3441dec9eac26e"), //
				commit("jeanlaurent", "02/01/2012", "Deuxieme commit", "649d3668d3ba68e75a3441dec9eac26e"), //
				commit("eric", "01/01/2012", "Premier commit", "77da98419ae312eb0e322a3dac44a734"));
		given(allCommits.list()).willReturn(commits);

		int exitCode = runZombieJsTest("HomePageTest.coffee");

		assertThat(exitCode).isZero();
	}

	static int runZombieJsTest(String jsFile) {
		return new Shell().execute("./mochalauncher.sh ./src/test/resources/%s %d", jsFile, server.service().getPort());
	}

	static Commit commit(String commiter, String date, String message, String avatarUrl) {
		Commit commit = mock(Commit.class);
		when(commit.getLogin()).thenReturn(commiter);
		when(commit.getDate()).thenReturn(date);
		when(commit.getMessage()).thenReturn(message);
		when(commit.getAvatarUrl()).thenReturn("https://secure.gravatar.com/avatar/" + avatarUrl);
		return commit;
	}
}
