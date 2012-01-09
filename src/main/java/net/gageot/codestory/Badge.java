package net.gageot.codestory;

public enum Badge {
	MOST_ACTIVE_COMMITER {
		public String fromRepository(Repository repository) {
			return repository.mostActiveCommiter();
		}
	},
	FIRST_COMMITER {
		public String fromRepository(Repository repository) {
			return repository.firstCommiter();
		}
	},
	LEAST_ACTIVE_COMMITER {
		public String fromRepository(Repository repository) {
			return repository.leastActiveCommiter();
		}
	};

	abstract String fromRepository(Repository repository);
}
