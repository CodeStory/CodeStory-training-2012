package net.gageot;

import com.google.common.base.*;

import java.io.*;

public class Badges {
	private final Repository respository;

	public Badges(Repository respository) {
		this.respository = respository;
	}

	public String get(Badge badge) {
		try {
			switch (badge) {
				case FIRST_COMMITER:
					return respository.firstCommiter();
				case MOST_ACTIVE_COMMITER:
					return respository.mostActiveCommiter();
				case LEAST_ACTIVE_COMMITER:
					return respository.leastActiveCommiter();
			}
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}

		throw new IllegalArgumentException("Unknown badge");
	}
}
