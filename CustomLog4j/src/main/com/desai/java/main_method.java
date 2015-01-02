package com.desai.java;

public class main_method {

	// static Logger logger = LogManager.getLogger(main_method.class);

	static org.apache.logging.log4j.Logger logger2 = org.apache.logging.log4j.LogManager
			.getLogger(main_method.class);

	private static short counter = (short) 2;

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
		final char sep = '-';
		final int JVM = (int) (System.currentTimeMillis() >>> 8);

		String s = new StringBuffer(36).append(format(getLoTime())).append(sep)
				.append(format(getCount())).toString();

		System.out.println(JVM);
		System.out.println(s);
	}

	private static String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	protected static String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	protected static int getLoTime() {
		return (int) System.currentTimeMillis();
	}

	static protected short getCount() {
		synchronized (main_method.class) {
			if (counter < 0)
				counter = 0;
			return counter++;
		}
	}
}
