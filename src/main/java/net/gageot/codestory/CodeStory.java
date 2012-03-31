package net.gageot.codestory;

import com.google.common.base.*;
import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.client.*;
import org.eclipse.egit.github.core.service.*;

import java.io.*;
import java.util.*;

import static com.google.common.collect.FluentIterable.*;
import static org.apache.commons.lang.StringUtils.*;

public class CodeStory {
	public List<Commit> commits(String login, String project) {
		return from(commitsFromGithub(login, project)).transform(TO_COMMIT).toImmutableList();
	}

	private static final Function<RepositoryCommit, Commit> TO_COMMIT = new Function<RepositoryCommit, Commit>() {
		@Override
		public Commit apply(RepositoryCommit commit) {
			User committer = commit.getCommitter();

			return new Commit(committer.getLogin(), substringBefore(committer.getAvatarUrl(), "?"), commit.getSha());
		}
	};

	private List<RepositoryCommit> commitsFromGithub(String login, String project) {
		GitHubClient client = GitHubClientFactory.create();

		try {
			Repository repository = new RepositoryService(client).getRepository(login, project);

			return new CommitService(client).getCommits(repository);
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}
}
