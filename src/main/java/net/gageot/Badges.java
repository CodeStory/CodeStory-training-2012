package net.gageot;

import com.google.common.base.*;

import java.io.*;

public class Badges {
	private final Repository repository;

	public Badges(Repository repository) {
		this.repository = repository;
	}

	public String get(Badge badge) {
		try {
			return badge.fromRepository(repository);
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}
}
