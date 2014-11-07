package com.desai.common.rmi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.desai.common.config.Rmi_App_Congif;

public class Host {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				Rmi_App_Congif.class);
		System.out.println("Waiting for request");
	}
}
