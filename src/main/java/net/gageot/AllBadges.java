package net.gageot;

import com.google.common.util.concurrent.*;
import com.google.inject.*;
import net.gageot.codestory.*;

import java.util.*;

public class AllBadges {

	private AllCommits allCommits;

	@Inject
	public AllBadges(AllCommits allCommits) {
		this.allCommits = allCommits;
	}

	public String topcommiter() {
		List<Commit> commits = allCommits.list();
		AtomicLongMap<String> results = AtomicLongMap.create();
		for (Commit commit : commits) {
			results.getAndIncrement(commit.getLogin());
		}

		/*
		return Ordering.natural().onResultOf(Functions.forMap(results.asMap())).max(results.asMap().keySet());
		*/
		String topCommiter = null;
		Map<String, Long> resultsMap = results.asMap();
		long max = 0;
		for (String key : resultsMap.keySet()) {
			long current = resultsMap.get(key);
			if (max < current) {
				max = current;
				topCommiter = key;
			}
		}
		return topCommiter;
	}
}
