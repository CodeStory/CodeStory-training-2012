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
		List<Commit> commits = asList(commit("jeanlaurent"), commit("jeanlaurent"), commit("dgageot"), commit("eric"));
		given(allCommits.list()).willReturn(commits);

		String name = allBadges.topCommiter();

		assertThat(name).isEqualTo("jeanlaurent");
	}

	private static Commit commit(String commiter) {
		return when(mock(Commit.class).getLogin()).thenReturn(commiter).getMock();
	}
}
