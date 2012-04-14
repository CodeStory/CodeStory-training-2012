package net.gageot;

import com.google.common.base.*;
import net.gageot.util.proxy.*;
import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.*;

import java.io.*;
import java.util.*;

public class AllCommits {
	private final String userName;
	private final String project;

	public AllCommits(String userName, String project) {
		this.userName = userName;
		this.project = project;
	}

	@Cached
	public List<RepositoryCommit> list() {
		try {
			return new CommitService().getCommits(new RepositoryService().getRepository(userName, project));
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}
}
