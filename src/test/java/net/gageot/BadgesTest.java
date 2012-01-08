package net.gageot;

import org.junit.*;

import java.io.*;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

public class BadgesTest {
	private Badges badges;
	private Repository respository = mock(Repository.class);

	@Before
	public void setUp() {
		badges = new Badges(respository);
	}

	@Test
	public void can_give_badge_to_first_commiter() throws IOException {
		when(respository.firstCommiter()).thenReturn("first_commiter");

		String commiter = badges.getWinner(Badge.FIRST_COMMITER);

		assertThat(commiter).isEqualTo("first_commiter");
	}
}
