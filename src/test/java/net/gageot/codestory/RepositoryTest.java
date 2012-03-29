package net.gageot.codestory;

import org.junit.*;

import static org.fest.assertions.Assertions.*;

public class RepositoryTest {
	private Repository repository;

	@Before
	public void setUp() {
		repository = new Repository("jlm", "NodeGravatar");
	}

	@Test
	public void can_get_first_commiter() {
		String commiter = repository.firstCommiter();

		assertThat(commiter).isEqualTo("jlm");
	}

	@Test
	public void can_get_most_active_commiter() {
		String commiter = repository.mostActiveCommiter();

		assertThat(commiter).isEqualTo("jlm");
	}

	@Test
	public void can_least_active_commiter() {
		String commiter = repository.leastActiveCommiter();

		assertThat(commiter).isEqualTo("dgageot");
	}
}
