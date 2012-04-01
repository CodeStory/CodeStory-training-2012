package net.gageot.testing;

import com.google.common.base.*;
import org.apache.commons.exec.*;

import java.io.*;

import static java.lang.String.*;

public class Shell {
	public int execute(String command, Object... arguments) {
		try {
			return new DefaultExecutor().execute(CommandLine.parse(format(command, arguments)));
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}
}
