package com.desai.common.autowiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Customer {

	private Person person;
	private int num;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	// @Autowired
	public Customer(Person person) {
		this.person = person;
		System.out.println("constructor");
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Autowired
	@Qualifier("PersonBean4")
	public void setPerson(Person person) {
		this.person = person;
		System.out.println("setter");
	}

	public Person getPerson() {
		return person;
	}

	@Override
	public String toString() {
		return "[ num: " + num + " Person@" + person + " ]";
	}

}
