package net.gageot.codestory;

import java.util.*;

import static com.google.common.collect.Lists.*;

public class CodeStory {
	public List<Commit> getCommitsFrom(String nodeGravatar) {
		return newArrayList(jlmCommit(), jlmCommit(), dgageotCommit(), dgageotCommit(), dgageotCommit(), dgageotCommit(), jlmCommit(), jlmCommit(), jlmCommit(), jlmCommit());
	}

	private Commit dgageotCommit() {
		return new Commit("dgageot", "https://secure.gravatar.com/avatar/f0887bf6175ba40dca795eb37883a8cf");
	}

	private Commit jlmCommit() {
		return new Commit("jlm", "https://secure.gravatar.com/avatar/649d3668d3ba68e75a3441dec9eac26e");
	}
}
