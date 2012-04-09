package net.gageot;

import lombok.*;

@Data
public class Commit {
	private final String avatarUrl;
	private final String commiter;
	private final String message;
	private final String date;
}
