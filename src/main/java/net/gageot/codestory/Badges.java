package net.gageot.codestory;

public class Badges {
	private final Repository repository;

	public Badges(Repository repository) {
		this.repository = repository;
	}

	public String get(Badge badge) {
		return badge.fromRepository(repository);
	}
}
