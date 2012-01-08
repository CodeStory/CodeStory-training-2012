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
}
