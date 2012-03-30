package net.gageot.codestory;

import org.eclipse.egit.github.core.client.*;

public final class GitHubClientFactory {
	private GitHubClientFactory() {
		// Static class
	}

	public static GitHubClient create() {
		return new GitHubClient(); // Internet
		//return new GitHubClient("github", -1, "http"); // GitHub Enterprise
	}
}
