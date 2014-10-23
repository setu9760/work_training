package main.java.com.desai.common.initial;

public class Person {
	private String name;
	private String address;
	private int age;
	private long ph_number;

	public Person() {
		
	}

	// <!-- ################################ -->
	/*
	 * Following code snippet is used in the Spring-Common.xml configuration
	 * file to declare this bean and inject the properties. This snippet used
	 * constructor injection method meaning it injects the argument via
	 * constructor. As in this example there are more than one constructors
	 * available we can use type and index to define which constructor to use.
	 */
	// <bean id="personBean" class="com.desai.common.Person">
	// <constructor-arg type="java.lang.String" index="0">
	// <value>1sdf ds </value>
	// </constructor-arg>
	// <constructor-arg type="java.lang.String" index="3">
	// <value>2"" abcd</value>
	// </constructor-arg>
	// <constructor-arg type="int" index="1">
	// <value>344</value>
	// </constructor-arg>
	// <constructor-arg type="long" index="2">
	// <value>499999999999</value>
	// </constructor-arg>
	// </bean>
	// <!-- ################################ -->

	public Person(String name, String address, int age, long ph_number) {
		this.name = name;
		this.address = address;
		this.age = age;
		this.ph_number = ph_number;
		System.out.println(1);
	}

	public Person(String address, int age, String name, long ph_number) {
		this.name = name;
		this.address = address;
		this.age = age;
		this.ph_number = ph_number;
		System.out.println(2);
	}

	public Person(String address, int age, long ph_number, String name) {
		this.name = name;
		this.address = address;
		this.age = age;
		this.ph_number = ph_number;
		System.out.println(3);
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
				+ " num " + ph_number;
	}
}
