package net.gageot;

import com.google.common.collect.*;
import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.*;

import java.io.*;
import java.util.*;

import static com.google.common.base.Objects.*;

public class Repository {
	private static Comparator<Long> MOST = Ordering.natural().nullsFirst();
	private static Comparator<Long> LEAST = Ordering.natural().reverse().nullsFirst();

	private final String user;
	private final String project;

	public Repository(String user, String project) {
		this.project = project;
		this.user = user;
	}

	public String firstCommiter() throws IOException {
		return commits().get(0).getAuthor().getLogin();
	}

	public String mostActiveCommiter() throws IOException {
		return commiterWithCommits(MOST);
	}

	public String leastActiveCommiter() throws IOException {
		return commiterWithCommits(LEAST);
	}

	public String commiterWithCommits(Comparator<Long> ordering) throws IOException {
		String commiter = user;
		Long count = null;

		for (Map.Entry<String, Long> entry : commitCountPerUser().entrySet()) {
			if (ordering.compare(entry.getValue(), count) > 0) {
				commiter = entry.getKey();
				count = entry.getValue();
			}
		}

		return commiter;
	}

	private List<RepositoryCommit> commits() throws IOException {
		return new CommitService().getCommits(new RepositoryId(user, project));
	}

	private Map<String, Long> commitCountPerUser() throws IOException {
		Map<String, Long> countPerUser = Maps.newHashMap();

		for (RepositoryCommit commit : commits()) {
			User author = commit.getAuthor();
			if (null == author) {
				continue;
			}

			String login = author.getLogin();
			long count = firstNonNull(countPerUser.get(login), 1L);
			countPerUser.put(author.getLogin(), count + 1);
		}

		return countPerUser;
	}
}
