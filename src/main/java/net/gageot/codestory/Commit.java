package net.gageot.codestory;

public class Commit {
	private String committer;
	private String imageUrl;

	public Commit(String committer, String imageURL) {
		this.committer = committer;
		this.imageUrl = imageURL;
	}

	public String getCommitter() {
		return committer;
	}

	public String getImageUrl() {
		return imageUrl;
	}
}
