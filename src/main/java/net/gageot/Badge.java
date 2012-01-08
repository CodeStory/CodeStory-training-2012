package net.gageot;

import java.io.*;

public enum Badge {
	MOST_ACTIVE_COMMITER {
		public String fromRepository(Repository repository) throws IOException {
			return repository.mostActiveCommiter();
		}
	},
	FIRST_COMMITER {
		public String fromRepository(Repository repository) throws IOException {
			return repository.firstCommiter();
		}
	},
	LEAST_ACTIVE_COMMITER {
		public String fromRepository(Repository repository) throws IOException {
			return repository.leastActiveCommiter();
		}
	};

	public abstract String fromRepository(Repository repository) throws IOException;
}
