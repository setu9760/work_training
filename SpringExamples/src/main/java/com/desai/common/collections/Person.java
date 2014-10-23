package com.desai.common.collections;

public class Person {
	private String name;
	private String address;
	private int age;
	private long ph_number;

	public Person() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getPh_number() {
		return ph_number;
	}

	public void setPh_number(long ph_number) {
		this.ph_number = ph_number;
	}

	@Override
	public String toString() {
		return "name: " + name + ". address " + address + " age " + age
				+ " num " + ph_number + "\n";
	}
}
