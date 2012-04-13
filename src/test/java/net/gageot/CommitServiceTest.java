package net.gageot;

import net.gageot.codestory.*;
import org.junit.*;

import java.util.*;

import static org.fest.assertions.Assertions.*;

public class CommitServiceTest {
	private CommitService commitService;

	@Before
	public void setUp() throws Exception {
		commitService = new CommitService("https://github.com/dgageot/NodeGravatar");
	}

	@Test
	public void should_count_commits() throws Exception {
		List<Commit> commits = commitService.listCommits();

		assertThat(commits).hasSize(10);
	}

	@Test
	public void should_retrieve_first_commit() throws Exception {
		Commit first = commitService.listCommits().get(0);

		assertThat(first.getLogin()).isEqualTo("jeanlaurent");
		assertThat(first.getAvatarUrl()).isEqualTo("https://secure.gravatar.com/avatar/649d3668d3ba68e75a3441dec9eac26e?s=140&d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png");
		assertThat(first.getDate()).isEqualTo("2012-03-29 06:57:48");
		assertThat(first.getMessage()).isEqualTo("removing file extensiosn");
	}
}
