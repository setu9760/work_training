package com.desai.java;

public class main_method {

	// static Logger logger = LogManager.getLogger(main_method.class);

	static org.apache.logging.log4j.Logger logger2 = org.apache.logging.log4j.LogManager
			.getLogger(main_method.class);

	private static short counter = (short) 0;

	public static void main(String[] args) {

		String[] array = new String[5];

		for (int i = 0; i < 5; i++) {
			array[i] = String.valueOf(i);
		}

		//
		// if (args.length < 2) {
		// System.out
		// .println("usage: java main_method <port> <http root directory>");
		// return;
		// }
		// new WebServer(Integer.parseInt(args[0]), args[1]);
		// for (int i = 0; i < 1000; i++) {
		// logger2.info("main method");
		// }
		final int JVM = (int) (System.currentTimeMillis() >>> 6);

		StringBuffer s = new StringBuffer(10);

		// String formatted = Integer.toHexString(JVM);
		// StringBuffer buf = new StringBuffer("000000");
		// buf.replace(0, 6, formatted);
		//
		// s.append(buf);

		String sub1 = Integer.toHexString(JVM);
		// sub1 = sub1.substring(1, 7);
		s.append(sub1);

		// String formatted2 = Integer.toHexString(counter);
		// StringBuffer buf2 = new StringBuffer("00");
		// buf2.replace(2 - formatted2.length(), 2, formatted2);
		//
		// s.append(buf2);
		String sub = format(getCounter());
		s.append(sub);

		System.out.println(JVM);
		System.out.println(s);
	}

	private static short getCounter() {
		synchronized (main_method.class) {
			if (counter < 0 || counter > 999)
				counter = 0;
			return counter++;
		}
	}

	private static String format(short shortVal) {
		String formatted = Integer.toHexString(shortVal);
		StringBuffer buf = new StringBuffer("000");
		buf.replace(3 - formatted.length(), 3, formatted);
		return buf.toString();
	}

}
