package net.gageot;

import org.eclipse.egit.github.core.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.runners.*;

import static java.util.Arrays.*;
import static org.fest.assertions.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AllBadgesTest {
	@Mock AllCommits allCommits;
	@InjectMocks AllBadges allBadges;

	@Test
	public void should_find_top_commiter() {
		given(allCommits.list()).willReturn(asList(commitBy("jeanlaurent"), commitBy("jeanlaurent"), commitBy("dgageot")));

		User user = allBadges.topCommiter();

		assertThat(user.getLogin()).isEqualTo("jeanlaurent");
	}

	@Test
	public void should_find_most_verbose_commiter() {
		given(allCommits.list()).willReturn(asList(commitBy("jeanlaurent", "SHORT"), commitBy("dgageot", "VERY VERY LONG")));

		User user = allBadges.mostVerboseCommitter();

		assertThat(user.getLogin()).isEqualTo("dgageot");
	}

	@Test
	public void should_find_fatty_commiter() {
		given(allCommits.list()).willReturn(asList(commitBy("jeanlaurent", 1000, 0), commitBy("dgageot", 10, 5), commitBy("dgageot", 0, 5)));

		User user = allBadges.fattyCommitter();

		assertThat(user.getLogin()).isEqualTo("jeanlaurent");
	}

	@Test
	public void should_find_slimmy_commiter() {
		given(allCommits.list()).willReturn(asList(commitBy("jeanlaurent", 1000, 0), commitBy("dgageot", 10, 5), commitBy("dgageot", 0, 5)));

		User user = allBadges.slimmyCommitter();

		assertThat(user.getLogin()).isEqualTo("dgageot");
	}

	static RepositoryCommit commitBy(String commiter) {
		return new RepositoryCommit().setAuthor(new User().setLogin(commiter));
	}

	static RepositoryCommit commitBy(String commiter, String message) {
		return commitBy(commiter).setCommit(new Commit().setMessage(message));
	}

	static RepositoryCommit commitBy(String commiter, int additions, int deletions) {
		return commitBy(commiter).setStats(new CommitStats().setAdditions(additions).setDeletions(deletions));
	}
}
