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
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd hh:mm:ss").withZoneUTC();
	private static final String USER = "dgageot";
	private static final String PROJECT = "NodeGravatar";

	public List<Commit> list() {
		return from(projectCommits()).transform(TO_COMMIT).toImmutableList();
	}

	private List<RepositoryCommit> projectCommits() {
		try {
			return new CommitService().getCommits(new RepositoryService().getRepository(USER, PROJECT));
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}

	private static String formatDate(Date date) {
		return DATE_FORMATTER.print(new DateTime(date, UTC));
	}

	private static final Function<RepositoryCommit, Commit> TO_COMMIT = new Function<RepositoryCommit, Commit>() {
		@Override
		public Commit apply(RepositoryCommit commit) {
			String login = commit.getCommitter().getLogin();
			String date = formatDate(commit.getCommit().getCommitter().getDate());
			String message = "removing file extensiosn";
			String avatarUrl = "https://secure.gravatar.com/avatar/649d3668d3ba68e75a3441dec9eac26e?s=140&d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png";

			return new Commit(login, date, message, avatarUrl);
		}
	};
}
