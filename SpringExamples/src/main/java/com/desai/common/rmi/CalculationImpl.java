package com.desai.common.rmi;

public class CalculationImpl implements Calculation {

	@Override
	public int cube(int number) {
		System.out.println("cube of " + number);
		return number * number * number;

	}

}
