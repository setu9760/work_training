package com.desai.logger;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.AbstractLookup;
import org.apache.logging.log4j.core.lookup.StrLookup;

@Plugin(name = "property", category = StrLookup.CATEGORY)
public class CustomLookup extends AbstractLookup {

	private static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	private final Object lockObject = new Object();

	private String customLogHeader;

	private static AtomicLong aLong = new AtomicLong(0);

	// flag to determine if environment variables are added to system
	// properties.
	private boolean isEnvPropsSet = false;

	// array of keys to ignore
	private final String[] keysToIgnore = { "user", "home", "system", "program" };

	@Override
	public String lookup(LogEvent event, String key) {

		// if this is the first call (i.e. system startup) we
		// don't log the system properties.
		if (aLong.getAndIncrement() == 0)
			return "";
		// on any subsequent calls i.e. on file rollover
		// we return the formatted string with system properties.
		else if (key.equalsIgnoreCase("customKey")) {
			synchronized (lockObject) {
				if (customLogHeader == null) {
					customLogHeader = prepareCustomLog();
				}
			}
			return customLogHeader;
		} else
			return "";

	}

	/**
	 * This method prepares the custom string in a readable format. It checks if
	 * the string is already prepared with the {@link #isStringReady} flag.
	 */
	private String prepareCustomLog() {

		StringBuilder formatted = new StringBuilder();
		StringBuilder javaProps = new StringBuilder();
		StringBuilder osProps = new StringBuilder();
		StringBuilder pathProps = new StringBuilder();
		StringBuilder otherPropes = new StringBuilder();

		// If environment properties are not added to system properties add
		// them now.
		if (!isEnvPropsSet)
			addEnvProps();

		Set<Object> set = System.getProperties().keySet();
		set.removeAll(getIgnoreList());
		Iterator<Object> i = set.iterator();

		formatted.append(LINE_SEPARATOR)
				.append("/*********** System properties *************/")
				.append(LINE_SEPARATOR);

		javaProps.append(LINE_SEPARATOR).append("        ")
				.append("JAVA Properties").append(LINE_SEPARATOR);

		osProps.append(LINE_SEPARATOR).append("        ")
				.append("OS Properties").append(LINE_SEPARATOR);

		pathProps.append(LINE_SEPARATOR).append("        ")
				.append("PATH variables and properties").append(LINE_SEPARATOR);

		otherPropes.append(LINE_SEPARATOR).append("        ")
				.append("Other Properties").append(LINE_SEPARATOR);

		while (i.hasNext()) {
			String key = i.next().toString();

			if (!shouldIgnore(key)) {
				if (key.contains("java"))
					append(javaProps, key);
				else if (key.contains("os"))
					append(osProps, key);
				else if (key.contains("path"))
					append(pathProps, key);
				else
					append(otherPropes, key);
			}
		}
		formatted.append(javaProps).append(osProps).append(pathProps)
				.append(otherPropes);
		formatted.append("/*********** System properties *************/")
				.append(LINE_SEPARATOR);

		return formatted.toString();
	}

	private void addEnvProps() {
		for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
			System.setProperty(entry.getKey(), entry.getValue());
		}
		isEnvPropsSet = true;
	}

	private void append(final StringBuilder builder, String key) {
		builder.append("                ").append(key).append(" : ")
				.append(System.getProperty(key)).append(LINE_SEPARATOR);
	}

	private boolean shouldIgnore(String key) {
		String lowerKey = key.toLowerCase();
		for (String match : keysToIgnore) {
			if (lowerKey.contains(match)) {
				return true;
			}
		}
		return false;
	}

	private void releaseMemory() {

	}

	/**
	 * This method returns a set of keys that we do not require to log, or the
	 * keys which may have some sort of private information associated with it.
	 * 
	 * @return set of keys to ignore when logging.
	 */
	private Set<Object> getIgnoreList() {
		Set<Object> ignore = new HashSet<Object>();

		ignore.add("M2");
		ignore.add("COMPUTERNAME");
		ignore.add("ProgramData");
		ignore.add("HOMEPATH");
		ignore.add("LOGONSERVER");
		ignore.add("LOCALAPPDATA");
		ignore.add("PUBLIC");
		ignore.add("APPDATA");
		return ignore;
	}
}
