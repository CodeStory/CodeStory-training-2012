package net.gageot;

import com.google.inject.*;
import net.gageot.test.rules.*;
import net.gageot.test.utils.*;
import net.gageot.util.dates.*;
import org.eclipse.egit.github.core.*;
import org.junit.*;

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
		given(allCommits.list()).willReturn(asList( //
				commit("dgageot", "2012-01-03", "Quatrieme commit", "f0887bf6175ba40dca795eb37883a8cf"), //
				commit("jeanlaurent", "2012-01-03", "Troisieme commit", "649d3668d3ba68e75a3441dec9eac26e"), //
				commit("jeanlaurent", "2012-01-02", "Deuxieme commit", "649d3668d3ba68e75a3441dec9eac26e"), //
				commit("eric", "2012-01-01", "Premier commit", "77da98419ae312eb0e322a3dac44a734")));

		int exitCode = runJsTest("HomePageTest.coffee");

		assertThat(exitCode).isZero();
	}

	static int runJsTest(String jsFile) {
		return new Shell().execute("./mochalauncher.sh ./src/test/resources/%s %d", jsFile, server.service().getPort());
	}

	static RepositoryCommit commit(String commiter, String date, String message, String avatarUrl) {
		return new RepositoryCommit() //
				.setAuthor(new User().setLogin(commiter).setAvatarUrl("https://secure.gravatar.com/avatar/" + avatarUrl)) //
				.setCommit(new Commit().setMessage(message).setAuthor(new CommitUser().setDate(DateFormats.parse(date))));
	}
}
