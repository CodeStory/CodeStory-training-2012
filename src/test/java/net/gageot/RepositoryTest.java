package net.gageot;

import org.junit.*;

import java.io.*;

import static org.fest.assertions.Assertions.*;

public class RepositoryTest {
	private Repository repository;

	@Before
	public void setUp() {
		repository = new Repository();
	}

	@Test
	public void can_get_first_commiter() throws IOException {
		String commiter = repository.firstCommiter("dgageot", "infinitest");

		assertThat(commiter).isEqualTo("dgageot");
	}
}
