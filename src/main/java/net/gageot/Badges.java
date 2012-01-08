package net.gageot;

import com.google.common.base.*;

import java.io.*;

public class Badges {
	private final Repository respository;

	public Badges(Repository respository) {
		this.respository = respository;
	}

	public String getWinner(Badge firstCommiter) {
		try {
			return respository.firstCommiter();
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}
}
