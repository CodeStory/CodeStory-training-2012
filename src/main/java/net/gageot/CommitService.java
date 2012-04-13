package net.gageot;

import com.google.inject.*;
import net.gageot.codestory.*;

import java.util.*;

import static com.google.common.collect.Lists.*;

@Singleton
public class CommitService {
	public CommitService(String projectUrl) {
	}

	public List<Commit> listCommits() {
		List<Commit> commits = newArrayList();
		for (int i = 0; i < 10; i++) {
			commits.add(new Commit("jeanlaurent", "2012-03-29 06:57:48", "removing file extensiosn", "https://secure.gravatar.com/avatar/649d3668d3ba68e75a3441dec9eac26e?s=140&d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png"));
		}
		return commits;
	}
}
