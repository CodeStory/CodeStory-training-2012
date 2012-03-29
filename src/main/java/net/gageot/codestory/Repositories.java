package net.gageot.codestory;

import com.google.common.collect.*;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.*;
import org.eclipse.egit.github.core.service.*;

import java.io.*;
import java.util.*;

public class Repositories {
	public List<String> list(String user) throws IOException {
		GitHubClient githubClient = new GitHubClient("github", -1, "http");

		List<String> names = Lists.newArrayList();

		for (Repository repo : new RepositoryService(githubClient).getRepositories(user)) {
			names.add(repo.getName());
		}

		return names;
	}
}
