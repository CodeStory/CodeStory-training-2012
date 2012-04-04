import org.apache.commons.exec.*;
import org.junit.*;

import java.io.*;

import static org.fest.assertions.Assertions.*;

public class HomePageTest {
	@Test
	public void should_display_homepage() throws IOException {
		int exitCode = new DefaultExecutor().execute(CommandLine.parse("/usr/local/bin/node HomePageTest.js"));

		assertThat(exitCode).isZero();
	}
}
