package net.gageot;

import com.google.inject.*;
import groovy.lang.Binding;
import groovy.lang.*;
import org.eclipse.egit.github.core.*;

import static com.google.common.collect.ImmutableMap.*;

public class AllBadges {
	@Inject AllCommits allCommits;

	public User topCommiter() {
		return (User) groovy("commits.groupBy { it.author.login }.max { it.value.size }.value[0].author");
	}

	public User mostVerboseCommitter() {
		return (User) groovy("commits.max { it.commit.message.size() }.author");
	}

	public User fattyCommitter() {
		return (User) groovy("commits.max { it.stats.additions - it.stats.deletions }.author");
	}

	public User slimmyCommitter() {
		return (User) groovy("commits.max { it.stats.deletions - it.stats.additions }.author");
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
