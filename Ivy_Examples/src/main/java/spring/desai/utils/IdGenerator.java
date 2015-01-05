package spring.desai.utils;

import java.io.Serializable;

public class IdGenerator {

	private static IdGenerator instance = new IdGenerator();

	private static short counter = (short) 0;

	public static IdGenerator getInstance() {
		return instance;
	}

	public Serializable generateId() {

		return null;
	}

}
