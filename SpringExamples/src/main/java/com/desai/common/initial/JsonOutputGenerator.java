package com.desai.common.initial;


public class JsonOutputGenerator implements IOutputGenerator {

	@Override
	public void generateOutput(String string) {
		/**
		 * Some logic to generate csv file here
		 */
		System.out.println("JSon generator got this string: " + string);
	}
}
