package com.desai.common.utils;

import java.io.Serializable;

public class GuidGenerator {

	private static GuidGenerator instance = new GuidGenerator();

	private static short counter = (short) 0;

	private static int JVM = (int) 0;

	public static GuidGenerator getInstance() {
		return instance;
	}

	public Serializable generateId() {

		JVM = (int) (System.currentTimeMillis() >>> 6);
		StringBuffer s = new StringBuffer(10);
		s.append(Integer.toHexString(JVM));
		s.append(format(getCounter()));

		return s.toString();
	}

	public String getGuid() throws GuidGeneratorException {
		try {
			return (String) generateId();
		} catch (RuntimeException e) {
			throw new GuidGeneratorException("Failed to generate Guid", e);
		}
	}

	private short getCounter() {
		synchronized (GuidGenerator.class) {
			if (counter < 0 || counter > 999)
				counter = 0;
			return counter++;
		}
	}

	private String format(short shortVal) {
		String formatted = Integer.toHexString(shortVal);
		StringBuffer buf = new StringBuffer("000");
		buf.replace(3 - formatted.length(), 3, formatted);
		return buf.toString();
	}

}
