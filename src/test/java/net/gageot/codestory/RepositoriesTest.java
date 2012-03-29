package net.gageot.codestory;

import org.eclipse.egit.github.core.client.*;
import org.junit.*;

import java.io.*;
import java.util.*;

import static org.fest.assertions.Assertions.*;

public class RepositoriesTest {
	private Repositories repositories;

	@Before
	public void setUp() {
		repositories = new Repositories();
	}

	@Test
	public void can_list_repositories_of_well_known_user() throws IOException {
		List<String> names = repositories.list("jlm");

		assertThat(names).contains("NodeGravatar");
	}

	@Test(expected = RequestException.class)
	public void fail_to_list_repositories_of_unknown_user() throws IOException {
		repositories.list("UNKOWN_UNKOWN_UNKOWN");
	}
}
