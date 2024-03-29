package net.gageot;

import net.gageot.codestory.*;
import org.eclipse.egit.github.core.*;
import org.junit.*;

import java.text.*;
import java.util.*;

import static org.fest.assertions.Assertions.*;

public class AllCommitsTest {
	static List<RepositoryCommit> commits;

	@BeforeClass
	public static void readCommits() {
		AllCommits allCommits = new AllCommits();
		allCommits.project = new Project("jlm", "NodeGravatar");
		commits = allCommits.list();
	}

	@Test
	public void should_count_commits() {
		assertThat(commits).hasSize(10);
	}

	@Test
	public void should_retrieve_first_commit() {
		RepositoryCommit first = commits.get(0);

		assertThat(first.getAuthor().getLogin()).isEqualTo("jlm");
		assertThat(first.getAuthor().getAvatarUrl()).isEqualTo("https://secure.gravatar.com/avatar/649d3668d3ba68e75a3441dec9eac26e?d=http://github%2Fimages%2Fgravatars%2Fgravatar-140.png");
		assertThat(first.getCommit().getAuthor().getDate()).isEqualTo(date("2012-03-29T17:57:48"));
		assertThat(first.getCommit().getMessage()).isEqualTo("removing file extensiosn");
		assertThat(first.getStats()).isNull();
	}

	@Test
	public void should_retrieve_authors() {
		assertThat(commits).onProperty("author.login").containsSequence("jlm", "jlm", "dgageot");
	}

	@Test
	public void should_retrieve_dates() {
		assertThat(commits).onProperty("commit.author.date").containsSequence(date("2012-03-29T17:57:48"), date("2012-03-29T17:56:20"), date("2012-03-29T17:46:13"));
	}

	@Test
	public void should_retrieve_messages() {
		assertThat(commits).onProperty("commit.message").containsSequence("removing file extensiosn", "Adding the right pictures", "Unused file");
	}

	@Test
	public void should_retrieve_gravatar_url() {
		assertThat(commits).onProperty("author.avatarUrl").containsSequence( //
				"https://secure.gravatar.com/avatar/649d3668d3ba68e75a3441dec9eac26e?d=http://github%2Fimages%2Fgravatars%2Fgravatar-140.png", //
				"https://secure.gravatar.com/avatar/649d3668d3ba68e75a3441dec9eac26e?d=http://github%2Fimages%2Fgravatars%2Fgravatar-140.png", //
				"https://secure.gravatar.com/avatar/f0887bf6175ba40dca795eb37883a8cf?d=http://github%2Fimages%2Fgravatars%2Fgravatar-140.png");
	}

	static Date date(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").parse(date);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid date");
		}
	}
}
