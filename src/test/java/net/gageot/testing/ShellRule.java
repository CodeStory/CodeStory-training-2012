package net.gageot.testing;

import com.google.common.base.*;
import org.apache.commons.exec.*;
import org.junit.rules.*;

import java.io.*;

import static java.lang.String.*;

public class ShellRule extends ExternalResource {
	private DefaultExecutor executor;

	@Override
	protected void before() {
		executor = new DefaultExecutor();
	}

	public int execute(String command, Object... arguments) {
		try {
			return executor.execute(CommandLine.parse(format(command, arguments)));
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}
}
