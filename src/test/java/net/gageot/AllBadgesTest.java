package net.gageot;

import net.gageot.codestory.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.runners.*;

import java.util.*;

import static java.util.Arrays.*;
import static org.fest.assertions.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AllBadgesTest {
	@Mock AllCommits allCommits;
	@InjectMocks AllBadges allBadges;

	@Test
	public void should_find_top_commiter() {
		List<Commit> commits = asList(commitBy("jeanlaurent"), commitBy("jeanlaurent"), commitBy("dgageot"));
		given(allCommits.list()).willReturn(commits);

		String name = allBadges.topCommiter();

		assertThat(name).isEqualTo("jeanlaurent");
	}

	@Test
	public void should_find_most_verbose_commiter() {
		List<Commit> commits = asList(commitBy("jeanlaurent", "SHORT"), commitBy("dgageot", "VERY VERY LONG"));
		given(allCommits.list()).willReturn(commits);

		String name = allBadges.mostVerboseCommitter();

		assertThat(name).isEqualTo("dgageot");
	}

	@Test
	public void should_find_fatty_commiter() {
		List<Commit> commits = asList(commitBy("jeanlaurent", 1000, 0), commitBy("dgageot", 10, 5), commitBy("dgageot", 0, 5));
		given(allCommits.list()).willReturn(commits);

		String name = allBadges.fattyCommitter();

		assertThat(name).isEqualTo("jeanlaurent");
	}

	@Test
	public void should_find_slimmy_commiter() {
		List<Commit> commits = asList(commitBy("jeanlaurent", 1000, 0), commitBy("dgageot", 10, 5), commitBy("dgageot", 0, 5));
		given(allCommits.list()).willReturn(commits);

		String name = allBadges.slimmyCommitter();

		assertThat(name).isEqualTo("dgageot");
	}

	static Commit commitBy(String commiter) {
		Commit commit = mock(Commit.class);
		when(commit.getLogin()).thenReturn(commiter);
		return commit;
	}

	static Commit commitBy(String commiter, String message) {
		Commit commit = commitBy(commiter);
		when(commit.getMessage()).thenReturn(message);
		return commit;
	}

	static Commit commitBy(String commiter, int additions, int deletions) {
		Commit commit = commitBy(commiter);
		when(commit.getAdditions()).thenReturn(additions);
		when(commit.getDeletions()).thenReturn(deletions);
		return commit;
	}
}
