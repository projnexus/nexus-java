package cc.projectnexus.adapters.java.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class JavaScriptUtils {

	public static String timestampJsToJava(String jsTimestamp) {
		DateTimeFormatter jsFormatter = DateTimeFormatter.ISO_INSTANT;
		DateTimeFormatter javaFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		String javaTimestampString;

		try {
			Instant instant = Instant.from(jsFormatter.parse(jsTimestamp));
			LocalDateTime javaDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
			javaTimestampString = javaDateTime.format(javaFormatter);
		} catch (Exception e) {
			e.printStackTrace();
			return null; // or return an empty string, depending on your preference
		}

		return javaTimestampString;
	}

}
