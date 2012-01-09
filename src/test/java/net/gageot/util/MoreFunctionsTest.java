package net.gageot.util;

import com.google.common.collect.*;
import org.junit.*;

import java.util.*;

import static com.google.common.collect.Iterables.*;
import static org.fest.assertions.Assertions.*;

public class MoreFunctionsTest {
	@Test
	public void can_convert_map_entry() {
		Map.Entry<String, Integer> entry = getOnlyElement(ImmutableMap.of("KEY", 10).entrySet());

		String key = MoreFunctions.<String>entryToKey().apply(entry);
		Integer value = MoreFunctions.<Integer>entryToValue().apply(entry);

		assertThat(key).isEqualTo("KEY");
		assertThat(value).isEqualTo(10);
	}
}
