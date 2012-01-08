package net.gageot;

import com.google.common.base.*;
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

	public String firstCommiter() {
		return commits().get(0).getAuthor().getLogin();
	}

	public String mostActiveCommiter() {
		return commiterWithCommits(MOST);
	}

	public String leastActiveCommiter() {
		return commiterWithCommits(LEAST);
	}

	public String commiterWithCommits(Comparator<Long> ordering) {
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

	private Map<String, Long> commitCountPerUser() {
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

	private List<RepositoryCommit> commits() {
		try {
			return new CommitService().getCommits(new RepositoryId(user, project));
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}
}
