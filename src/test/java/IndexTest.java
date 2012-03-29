import org.apache.commons.exec.*;
import org.junit.*;

import static junit.framework.Assert.*;

public class IndexTest {
	@Test
	public void should_display_project_name() throws Exception {
		DefaultExecutor executor = new DefaultExecutor();
		int result = executor.execute(CommandLine.parse("/usr/local/bin/node test.js"));
		if (result != 0) {
			fail("ERROR");
		}
	}
}
