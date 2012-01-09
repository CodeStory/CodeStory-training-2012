package net.gageot.codestory;

import com.google.common.base.*;
import com.google.common.collect.*;
import net.gageot.util.Count;
import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.*;

import java.io.*;
import java.util.*;

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

		for (Map.Entry<String, Long> entry : commitCountPerUser().asMap().entrySet()) {
			if (ordering.compare(entry.getValue(), count) > 0) {
				commiter = entry.getKey();
				count = entry.getValue();
			}
		}

		return commiter;
	}

	private Count<String> commitCountPerUser() {
		Count<String> countPerUser = Count.create();

		for (RepositoryCommit commit : commits()) {
			User author = commit.getAuthor();
			if (null != author) {
				countPerUser.add(author.getLogin(), 1L);
			}
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
