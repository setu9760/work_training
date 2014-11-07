package com.desai.common.initial;


public class CsvOutputGenerator implements IOutputGenerator {

	@Override
	public void generateOutput(String string) {
		/**
		 * Some logic to generate csv file here
		 */
		System.out.println("CSV generator got this string: " + string);
	}
}
