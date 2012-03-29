package net.gageot.codestory;

import org.junit.*;

import java.util.*;

import static org.fest.assertions.Assertions.*;

public class CodeStoryTest {
	private static final String JL_GRAVATAR = "https://secure.gravatar.com/avatar/649d3668d3ba68e75a3441dec9eac26e";
	private static final String DAVID_GRAVATAR = "https://secure.gravatar.com/avatar/f0887bf6175ba40dca795eb37883a8cf";

	List<Commit> commits = new CodeStory().getCommitsFrom("NodeGravatar");

	@Test
	public void should_get_commit_count_out_of_a_repository_name() {
		assertThat(commits).hasSize(10);
	}

	@Test
	public void should_get_committer_names_of_a_repository_commits() {
		assertThat(commits).onProperty("committer").containsExactly("jlm", "jlm", "dgageot", "dgageot", "dgageot", "dgageot", "jlm", "jlm", "jlm", "jlm");
	}

	@Test
	public void should_get_image_url_of_a_repository_commits() {
		assertThat(commits).onProperty("imageUrl").containsExactly(JL_GRAVATAR, JL_GRAVATAR, DAVID_GRAVATAR, DAVID_GRAVATAR, DAVID_GRAVATAR, DAVID_GRAVATAR, JL_GRAVATAR, JL_GRAVATAR, JL_GRAVATAR, JL_GRAVATAR);
	}

	@Test
	public void should_get_sha1_from_commit() {
		assertThat(commits).onProperty("sha1").containsSequence("710ff33fed6d4b295f9e792bcf722c622a51d2f0", "590db31030e4b9937bb2c6d74f481c3c551bee9e");
	}
}
