package com.desai.common.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.desai.common.autowiring.Customer;

public class App_autowiring {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-Autowiring.xml");
		Customer customer = (Customer) context.getBean("CustomerBean");
		System.out.println(customer);
	}
}
