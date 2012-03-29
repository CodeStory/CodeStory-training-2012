package net.gageot.codestory;

import com.google.common.base.*;
import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.client.*;
import org.eclipse.egit.github.core.service.*;

import java.io.*;
import java.util.*;

import static com.google.common.collect.Lists.*;

public class CodeStory {
	public List<Commit> getCommitsFrom(String nodeGravatar) {
		List<RepositoryCommit> commits = getCommitsFromGithub();
		return transform(commits, new Function<RepositoryCommit, Commit>() {
			@Override
			public Commit apply(RepositoryCommit input) {
				return new Commit(input.getCommitter().getLogin(), input.getCommitter().getAvatarUrl(), input.getSha());
			}
		});
	}

	private List<RepositoryCommit> getCommitsFromGithub() {
		try {
			GitHubClient client = new GitHubClient("github", -1, "http");
			Repository repository = new RepositoryService(client).getRepository("jlm", "NodeGravatar");
			return new CommitService(client).getCommits(repository);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
