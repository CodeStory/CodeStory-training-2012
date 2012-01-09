package net.gageot.util;

import com.google.common.collect.*;

import java.util.*;

public class Count<T> {
	private final Map<T, Long> count;

	private Count() {
		count = Maps.newHashMap();
	}

	public static <T> Count<T> create() {
		return new Count<T>();
	}

	public long add(T object, long increment) {
		return set(object, get(object) + increment);
	}

	public long substract(T object, long decrement) {
		return set(object, get(object) - decrement);
	}

	public long set(T object, long value) {
		count.put(object, value);
		return value;
	}

	public long get(T object) {
		Long value = count.get(object);
		return (null == value) ? 0 : value;
	}

	public void remove(T object) {
		count.remove(object);
	}

	public Set<T> keySet() {
		return Collections.unmodifiableSet(count.keySet());
	}

	public Set<Map.Entry<T, Long>> entrySet() {
		return Collections.unmodifiableSet(count.entrySet());
	}

	public int size() {
		return count.size();
	}

	public void reset() {
		count.clear();
	}
}
