package net.gageot.codestory;

import lombok.*;

@Data
public class Commit {
	private final String login;
	private final String date;
	private final String message;
	private final String avatarUrl;
}
