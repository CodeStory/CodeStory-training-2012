import com.google.common.io.*;
import org.fest.assertions.*;
import org.junit.*;

import java.io.*;
import java.nio.charset.*;

public class IndexTest {
	@Test
	public void should_display_project_name() throws Exception {
		String indexContent = Resources.toString(new File("src/main/resources/index.html").toURI().toURL(), Charset.forName("UTF-8"));
		Assertions.assertThat(indexContent).contains("id='project-name'".replace("'", "\""));
	}
}
