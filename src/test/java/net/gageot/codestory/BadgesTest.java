package net.gageot.codestory;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.runners.*;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BadgesTest {
	@InjectMocks Badges badges;
	@Mock Repository respository;

	@Test
	public void can_give_badge_to_first_commiter() {
		when(respository.firstCommiter()).thenReturn("first_commiter");

		String commiter = badges.get(Badge.FIRST_COMMITER);

		assertThat(commiter).isEqualTo("first_commiter");
	}

	@Test
	public void can_give_badge_to_most_active_commiter() {
		when(respository.mostActiveCommiter()).thenReturn("most_active_commiter");

		String commiter = badges.get(Badge.MOST_ACTIVE_COMMITER);

		assertThat(commiter).isEqualTo("most_active_commiter");
	}

	@Test
	public void can_give_badge_to_least_active_commiter() {
		when(respository.leastActiveCommiter()).thenReturn("least_active_commiter");

		String commiter = badges.get(Badge.LEAST_ACTIVE_COMMITER);

		assertThat(commiter).isEqualTo("least_active_commiter");
	}
}
