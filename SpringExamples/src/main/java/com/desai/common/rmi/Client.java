package com.desai.common.rmi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.desai.common.config.Rmi_Client_Config;

public class Client {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				Rmi_Client_Config.class);
		Calculation calculation = context.getBean(Calculation.class);
		System.out.println(calculation.cube(3));
	}

}
