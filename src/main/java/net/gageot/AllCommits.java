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

import static com.google.common.collect.Lists.*;
import static org.joda.time.format.DateTimeFormat.*;

@Singleton
public class AllCommits {
	private static final DateTimeFormatter DATE_TIME_FORMATTER = forPattern("yyyy-MM-dd hh:mm:ss");
	private static final DateTimeZone UTC = DateTimeZone.forID("UTC");

	public List<Commit> list() {
		try {
			Repository repository = new RepositoryService().getRepository("dgageot", "NodeGravatar");
			List<RepositoryCommit> repositoryCommit = new CommitService().getCommits(repository);

			List<Commit> commits = newArrayList();
			for (RepositoryCommit commit : repositoryCommit) {
				commits.add(new Commit(commit.getCommitter().getLogin(), formatDate(commit.getCommit().getAuthor().getDate()), "removing file extensiosn", "https://secure.gravatar.com/avatar/649d3668d3ba68e75a3441dec9eac26e?s=140&d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png"));

			}

			return commits;
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}

	private String formatDate(Date date) {
		return DATE_TIME_FORMATTER.print(new DateTime(date, UTC));
	}
}
