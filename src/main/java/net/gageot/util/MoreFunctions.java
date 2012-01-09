package net.gageot.util;

import com.google.common.base.*;

import java.util.*;

public class MoreFunctions {
	private MoreFunctions() {
		// Static utility class
	}

	public static <V> Function<Map.Entry<?, V>, V> entryToValue() {
		return new Function<Map.Entry<?, V>, V>() {
			@Override
			public V apply(Map.Entry<?, V> entry) {
				return entry.getValue();
			}
		};
	}

	public static <K> Function<Map.Entry<K, ?>, K> entryToKey() {
		return new Function<Map.Entry<K, ?>, K>() {
			@Override
			public K apply(Map.Entry<K, ?> entry) {
				return entry.getKey();
			}
		};
	}
}
