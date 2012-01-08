package net.gageot;

import org.junit.*;

import java.io.*;

import static org.fest.assertions.Assertions.*;

public class RepositoryTest {
	private Repository repository;

	@Before
	public void setUp() {
		repository = new Repository("dgageot", "infinitest");
	}

	@Test
	public void can_get_first_commiter() throws IOException {
		String commiter = repository.firstCommiter();

		assertThat(commiter).isEqualTo("dgageot");
	}

	@Test
	public void can_get_most_active_commiter() throws IOException {
		String commiter = repository.mostActiveCommiter();

		assertThat(commiter).isEqualTo("dgageot");
	}

	@Test
	public void can_least_active_commiter() throws IOException {
		String commiter = repository.leastActiveCommiter();

		assertThat(commiter).isEqualTo("manandbytes");
	}
}
