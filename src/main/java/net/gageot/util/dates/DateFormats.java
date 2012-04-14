package net.gageot.util.dates;

import org.joda.time.*;
import org.joda.time.format.*;

import java.util.*;

public final class DateFormats {
	private static final DateTimeZone UTC = DateTimeZone.forID("UTC");
	private static final DateTimeFormatter FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd").withZoneUTC();

	private DateFormats() {
		// static class
	}

	public static String format(Date date) {
		return FORMAT.print(new DateTime(date, UTC));
	}

	public static Date parse(String date) {
		return FORMAT.parseDateTime(date).toDate();
	}
}
