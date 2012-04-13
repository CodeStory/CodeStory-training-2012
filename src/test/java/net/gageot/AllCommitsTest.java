package net.gageot;

import net.gageot.codestory.*;
import org.junit.*;

import java.util.*;

import static org.fest.assertions.Assertions.*;

public class AllCommitsTest {
	static List<Commit> commits = new AllCommits().list("dgageot", "NodeGravatar");

	@Test
	public void should_count_commits() {
		assertThat(commits).hasSize(10);
	}

	@Test
	public void should_retrieve_first_commit() {
		Commit first = commits.get(0);

		assertThat(first.getLogin()).isEqualTo("jeanlaurent");
		assertThat(first.getAvatarUrl()).isEqualTo("https://secure.gravatar.com/avatar/649d3668d3ba68e75a3441dec9eac26e?d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png");
		assertThat(first.getDate()).isEqualTo("2012-03-29");
		assertThat(first.getMessage()).isEqualTo("removing file extensiosn");
	}

	@Test
	public void should_retrieve_all_authors() {
		assertThat(commits).onProperty("login").containsExactly(//
				"jeanlaurent", "jeanlaurent", //
				"dgageot", "dgageot", "dgageot", "dgageot", //
				"jeanlaurent", "jeanlaurent", "jeanlaurent", "jeanlaurent" //
		);
	}

	@Test
	public void should_retrieve_dates() {
		assertThat(commits).onProperty("date").containsOnly("2012-03-29");
	}

	@Test
	public void should_retrieve_messages() {
		assertThat(commits).onProperty("message").containsSequence("removing file extensiosn", "Adding the right pictures", "Unused file");
	}

	@Test
	public void should_retrieve_gravatar_url() {
		assertThat(commits).onProperty("avatarUrl").containsSequence( //
				"https://secure.gravatar.com/avatar/649d3668d3ba68e75a3441dec9eac26e?d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png", //
				"https://secure.gravatar.com/avatar/649d3668d3ba68e75a3441dec9eac26e?d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png", //
				"https://secure.gravatar.com/avatar/f0887bf6175ba40dca795eb37883a8cf?d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png");
	}
}
