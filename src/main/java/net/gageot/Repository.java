package net.gageot;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.*;

import java.io.*;

public class Repository {
	private final String user;
	private final String project;

	public Repository(String user, String project) {
		this.project = project;
		this.user = user;
	}

	public String firstCommiter() throws IOException {
		return new CommitService().getCommits(new RepositoryId(user, project)).get(0).getAuthor().getLogin();
	}
}
