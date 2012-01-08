package net.gageot;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.*;

import java.io.*;
import java.util.*;

public class Repository {
	public String firstCommiter(String user, String project) throws IOException {
		List<RepositoryCommit> commits = commits(user, project);

		RepositoryCommit firstCommit = commits.get(0);

		return firstCommit.getAuthor().getLogin();
	}

	private List<RepositoryCommit> commits(String user, String project) throws IOException {
		return new CommitService().getCommits(new RepositoryId(user, project));
	}
}
