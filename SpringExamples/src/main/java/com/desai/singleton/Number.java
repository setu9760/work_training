package com.desai.singleton;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

//@Service("numberBean")
//@Scope("prototype")
public class Number {

	private int number;

	public Number() {
	}

	public Number(int number) {
		super();
		this.number = number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void printnumber_incr() {
		System.out.println("number is: " + number);
		number++;
	}
}
