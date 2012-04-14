package net.gageot;

import com.google.inject.*;
import groovy.lang.Binding;
import groovy.lang.*;

import static com.google.common.collect.ImmutableMap.*;

public class AllBadges {
	@Inject AllCommits allCommits;

	public String topCommiter() {
		return (String) groovy("commits.countBy { it.login }.max { it.value }.key");
	}

	public String mostVerboseCommitter() {
		return (String) groovy("commits.max { it.message.size() }.login");
	}

	public String fattyCommitter() {
		return (String) groovy("commits.max { it.additions - it.deletions }.login");
	}

	public String slimmyCommitter() {
		return (String) groovy("commits.max { it.deletions - it.additions }.login");
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
