package net.gageot.codestory;

public class Commit {
	private String committer;
	private String imageUrl;
	private String sha1;
	private String date;

	public Commit(String committer, String imageURL, String sha1) {
		this.committer = committer;
		this.imageUrl = imageURL;
		this.sha1 = sha1;
		this.date = "29/03/2012";
	}

	public String getCommitter() {
		return committer;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getSha1() {
		return sha1;
	}

	public String getDate() {
		return date;
	}
}
