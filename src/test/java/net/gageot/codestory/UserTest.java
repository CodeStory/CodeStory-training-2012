package net.gageot.codestory;

import org.junit.*;

import java.io.*;

import static org.fest.assertions.Assertions.*;

public class UserTest {
	GitHubUser user;

	@Before
	public void getUser() throws IOException {
		user = new GitHubUser("dgageot");
	}

	@Test
	public void findAnEmailFromAUser() {
		assertThat(user.getEmail()).isEqualTo("david@gageot.net");
	}

	@Test
	public void findAnAvatarFromAUser() {
		assertThat(user.getAvatarUrl()).startsWith("https://secure.gravatar.com/avatar/f0887bf6175ba40dca795eb37883a8cf");
	}

	@Test
	public void findTheNameFromAUser() {
		assertThat(user.getName()).isEqualTo("David Gageot");
	}
}
