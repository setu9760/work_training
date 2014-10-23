package com.desai.common.collections;

import org.springframework.beans.factory.InitializingBean;

/**
 * We have implemented bean life-cycle method callback interface, it implements
 * related life-cycle method which in this case is "afterPropertiesSet()". all
 * it does is pre-initialisation of bean before the it is injected by spring
 * container
 * 
 * @author desai
 * 
 */
public class Person implements InitializingBean {
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

	/**
	 * implemented life-cycle callback method
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if (name != null && address != null) {
			System.out.println("name  " + name + " initialised");
		}
	}

	public void onDestroy() {
		System.out.println("onDestroy called for " + name);
	}
}
