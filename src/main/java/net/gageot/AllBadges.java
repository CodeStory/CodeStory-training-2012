package net.gageot;

import com.google.inject.*;
import groovy.lang.Binding;
import groovy.lang.*;

import static com.google.common.collect.ImmutableMap.*;

public class AllBadges {
	private AllCommits allCommits;

	@Inject
	public AllBadges(AllCommits allCommits) {
		this.allCommits = allCommits;
	}

	public String topCommiter() {
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
