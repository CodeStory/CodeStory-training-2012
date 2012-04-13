package net.gageot;

import com.google.common.base.*;
import com.google.inject.*;
import net.gageot.codestory.Commit;
import net.gageot.util.dates.*;
import net.gageot.util.proxy.*;
import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.*;

import java.io.*;
import java.util.*;

import static com.google.common.collect.FluentIterable.*;

public class AllCommits {
	private final String userName;
	private final String project;

	@Inject
	public AllCommits(String userName, String project) {
		this.userName = userName;
		this.project = project;
	}

	@Cached
	public List<Commit> list() {
		return from(projectCommits()).transform(TO_COMMIT).toImmutableList();
	}

	private List<RepositoryCommit> projectCommits() {
		try {
			return new CommitService().getCommits(new RepositoryService().getRepository(userName, project));
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}

	private static Function<RepositoryCommit, Commit> TO_COMMIT = new Function<RepositoryCommit, Commit>() {
		@Override
		public Commit apply(RepositoryCommit commit) {
			String login = null == commit.getAuthor() ? "Unknown" : commit.getAuthor().getLogin();
			String date = DateFormats.format(commit.getCommit().getAuthor().getDate());
			String message = commit.getCommit().getMessage();
			String avatarUrl = null == commit.getAuthor() ? "" : commit.getAuthor().getAvatarUrl();

			return new Commit(login, date, message, avatarUrl);
		}
	};
}
