package net.gageot.util;

import org.junit.*;

import static org.fest.assertions.Assertions.*;
import static org.fest.assertions.MapAssert.*;

public class CountTest {
	private Count<String> count;

	@Before
	public void setUp() {
		count = Count.create();
	}

	@Test
	public void can_get_default_value() {
		assertThat(count.get("KEY")).isZero();
	}

	@Test
	public void can_set_value() {
		assertThat(count.set("KEY", 10)).isEqualTo(10);
		assertThat(count.get("KEY")).isEqualTo(10);
	}

	@Test
	public void can_add_value() {
		assertThat(count.add("KEY", 10)).isEqualTo(10);
		assertThat(count.get("KEY")).isEqualTo(10);
	}

	@Test
	public void can_substract_value() {
		assertThat(count.add("KEY", 10)).isEqualTo(10);
		assertThat(count.substract("KEY", 7)).isEqualTo(3);
		assertThat(count.get("KEY")).isEqualTo(3);
	}

	@Test
	public void can_add_multiple_times() {
		assertThat(count.add("KEY", 10)).isEqualTo(10);
		assertThat(count.add("KEY", 5)).isEqualTo(10 + 5);
	}

	@Test
	public void can_get_keys() {
		count.add("KEY1", 10);
		count.add("KEY2", 10);

		assertThat(count.getKeys()).containsOnly("KEY1", "KEY2");
	}

	@Test
	public void can_get_as_map() {
		count.add("KEY1", 10);
		count.add("KEY2", 20);

		assertThat(count.asMap()).includes(entry("KEY1", 10L), entry("KEY2", 20L));
	}

	@Test
	public void can_remove_key() {
		count.add("KEY1", 10);
		assertThat(count.size()).isEqualTo(1);

		count.remove("KEY1");
		assertThat(count.size()).isZero();
	}

	@Test
	public void can_reset() {
		count.add("KEY", 10);
		assertThat(count.get("KEY")).isEqualTo(10);

		count.reset();
		assertThat(count.get("KEY")).isEqualTo(0);
	}
}
