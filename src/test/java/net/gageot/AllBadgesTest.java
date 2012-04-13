package net.gageot;

import net.gageot.codestory.*;
import org.junit.*;
import org.mockito.*;

import java.util.*;

import static java.util.Arrays.*;
import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

public class AllBadgesTest {
	private AllCommits allCommits = Mockito.mock(AllCommits.class);
	private AllBadges allBadges = new AllBadges(allCommits);

	@Test
	public void shouldFindTheTopCommiter() {
		when(allCommits.list()).thenReturn(commitList());

		String name = allBadges.topcommiter();

		assertThat(name).isEqualTo("jeanlaurent");
	}

	@Test
	public void should_get_top_commiter() {
		when(allCommits.list()).thenReturn(commitList());

		String name = allBadges.getTopCommiterGroovy();

		assertThat(name).isEqualTo("jeanlaurent");
	}

	private static List<Commit> commitList() {
		return asList(commitFrom("jeanlaurent"), commitFrom("jeanlaurent"), commitFrom("David"), commitFrom("Eric"));
	}

	private static Commit commitFrom(String commiter) {
		return new Commit(commiter, "", "", "");
	}
}
