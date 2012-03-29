package net.gageot.codestory;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.client.*;
import org.eclipse.egit.github.core.service.*;

import java.io.*;

public class GitHubUser {
	private User user;

	public GitHubUser(String login) throws IOException {
		this.user = new UserService(new GitHubClient("github", -1, "http")).getUser(login);
	}

	public String getEmail() {
		return user.getEmail();
	}

	public String getAvatarUrl() {
		return user.getAvatarUrl();
	}

	public String getName() {
		return user.getName();
	}
}
