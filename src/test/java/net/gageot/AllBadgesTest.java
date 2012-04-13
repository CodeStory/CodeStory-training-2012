package net.gageot;

import net.gageot.codestory.*;
import org.junit.*;

import java.util.*;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

public class AllBadgesTest {

	@Test
	public void shouldFindTheTopCommiter() {
		AllCommits allCommits = mock(AllCommits.class);
		when(allCommits.list()).thenReturn(commitList());
		String name = new AllBadges(allCommits).topcommiter();
		assertThat(name).isEqualTo("jeanlaurent");
	}

	private List<Commit> commitList() {
		return Arrays.asList(commitFrom("jeanlaurent"), commitFrom("jeanlaurent"), commitFrom("David"), commitFrom("Eric"));
	}

	private Commit commitFrom(String commiter) {
		return new Commit(commiter, "", "", "");
	}
}
