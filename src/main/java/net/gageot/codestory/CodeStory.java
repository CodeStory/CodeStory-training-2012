package net.gageot.codestory;

import java.util.*;

import static com.google.common.collect.Lists.*;

public class CodeStory {
	public List<Commit> getCommitsFrom(String nodeGravatar) {
		return newArrayList( //
				jlmCommit("710ff33fed6d4b295f9e792bcf722c622a51d2f0"), //
				jlmCommit("590db31030e4b9937bb2c6d74f481c3c551bee9e"), //
				dgageotCommit(), //
				dgageotCommit(), //
				dgageotCommit(), //
				dgageotCommit(), //
				jlmCommit("710ff33fed6d4b295f9e792bcf722c622a51d2f0"), //
				jlmCommit("710ff33fed6d4b295f9e792bcf722c622a51d2f0"), //
				jlmCommit("710ff33fed6d4b295f9e792bcf722c622a51d2f0"), //
				jlmCommit("710ff33fed6d4b295f9e792bcf722c622a51d2f0"));
	}

	private Commit dgageotCommit() {
		return new Commit("dgageot", "https://secure.gravatar.com/avatar/f0887bf6175ba40dca795eb37883a8cf", "");
	}

	private Commit jlmCommit(String sha1) {
		return new Commit("jlm", "https://secure.gravatar.com/avatar/649d3668d3ba68e75a3441dec9eac26e", sha1);
	}
}
