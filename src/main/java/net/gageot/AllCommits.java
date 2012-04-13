package net.gageot;

import com.google.common.base.*;
import com.google.inject.*;
import net.gageot.codestory.Commit;
import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.*;
import org.joda.time.*;
import org.joda.time.format.*;

import java.io.*;
import java.util.*;

import static com.google.common.collect.FluentIterable.*;

@Singleton
public class AllCommits {
	private static final DateTimeZone UTC = DateTimeZone.forID("UTC");
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd").withZoneUTC();

	public List<Commit> list(String userName, String project) {
		return from(projectCommits(userName, project)).transform(TO_COMMIT).toImmutableList();
	}

	private List<RepositoryCommit> projectCommits(String userName, String project) {
		try {
			return new CommitService().getCommits(new RepositoryService().getRepository(userName, project));
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}

	private static String formatDate(Date date) {
		return DATE_FORMATTER.print(new DateTime(date, UTC));
	}

	private static Function<RepositoryCommit, Commit> TO_COMMIT = new Function<RepositoryCommit, Commit>() {
		@Override
		public Commit apply(RepositoryCommit commit) {
			String login = null == commit.getAuthor() ? "Unknown" : commit.getAuthor().getLogin();
			String date = formatDate(commit.getCommit().getAuthor().getDate());
			String message = commit.getCommit().getMessage();
			String avatarUrl = null == commit.getAuthor() ? "" : commit.getAuthor().getAvatarUrl();

			return new Commit(login, date, message, avatarUrl);
		}
	};
}
