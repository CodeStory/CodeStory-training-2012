package net.gageot;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.*;

public class Test {
	public static void main(String[] args) throws Exception {
		RepositoryService service = new RepositoryService();
		service.getClient().setCredentials("dgageot", "tibidabo2048");

		for (Repository repo : service.getRepositories("defunkt")) {
			System.out.println(repo.getName() + " Watchers: " + repo.getWatchers());
		}
	}
}
