package com.desai.java;

public class main_method {

	// static Logger logger = LogManager.getLogger(main_method.class);

	static org.apache.logging.log4j.Logger logger2 = org.apache.logging.log4j.LogManager
			.getLogger(main_method.class);

	private static short counter = (short) 39;

	public static void main(String[] args) {

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
		final int JVM = (int) (System.currentTimeMillis() >>> 1);

		StringBuffer s = new StringBuffer(10);

		// String formatted = Integer.toHexString(JVM);
		// StringBuffer buf = new StringBuffer("000000");
		// buf.replace(0, 6, formatted);
		//
		// s.append(buf);
		s.replace(0, 6, Integer.toHexString(JVM).substring(1, 7));

		// String formatted2 = Integer.toHexString(counter);
		// StringBuffer buf2 = new StringBuffer("00");
		// buf2.replace(2 - formatted2.length(), 2, formatted2);
		//
		// s.append(buf2);
		String sub = Integer.toHexString(counter).substring(0, 2);
		s.append(sub);

		System.out.println(JVM);
		System.out.println(s);
	}
}
