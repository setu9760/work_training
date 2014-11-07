package com.desai.common.rmi;

public class CalculationImpl implements Calculation {

	@Override
	public int cube(int number) {
		return number * number * number;
	}

}
