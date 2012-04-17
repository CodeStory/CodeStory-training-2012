package net.gageot;

import com.google.common.base.*;
import com.google.inject.*;
import net.gageot.codestory.*;
import net.gageot.util.proxy.*;
import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.client.*;
import org.eclipse.egit.github.core.service.*;

import java.io.*;
import java.util.*;

public class AllCommits {
	@Inject Project project;

	@Cached
	public List<RepositoryCommit> list() {
		try {
			//GitHubClient githubClient = new GitHubClient("github", -1, "http");
			GitHubClient githubClient = new GitHubClient();
			return new CommitService(githubClient).getCommits(new RepositoryService(githubClient).getRepository(project.getUserName(), project.getProject()));
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}
}
