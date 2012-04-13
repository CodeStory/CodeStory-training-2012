package net.gageot;

import com.google.common.util.concurrent.*;
import com.google.inject.*;
import groovy.lang.Binding;
import groovy.lang.*;
import net.gageot.codestory.*;

import java.util.*;

import static com.google.common.collect.ImmutableMap.*;

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

	public String getTopCommiterGroovy() {
		return (String) groovy("commits.countBy { it.login }.max { it.value }.key");
	}

	private Object groovy(String script) {
		try {
			GroovyShell shell = new GroovyShell(new Binding(of("commits", allCommits.list())));

			return shell.evaluate(script);
		} catch (Exception e) {
			throw new RuntimeException("unable to understand the groovy script", e);
		}
	}
}
