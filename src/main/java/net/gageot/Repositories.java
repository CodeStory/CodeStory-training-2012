package net.gageot;

import com.google.common.collect.*;
import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.*;

import java.io.*;
import java.util.*;

public class Repositories {
	public List<String> list(String user) throws IOException {
		List<String> names = Lists.newArrayList();

		for (Repository repo : new RepositoryService().getRepositories(user)) {
			names.add(repo.getName());
		}

		return names;
	}
}
