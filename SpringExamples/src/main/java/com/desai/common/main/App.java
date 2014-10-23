package com.desai.common.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.desai.common.config.AppConfig;
import com.desai.common.interfaces.IOutputGenerator;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);
		IOutputGenerator generator = (IOutputGenerator) context
				.getBean("JsonOutputGeneratorBean");
		generator.generateOutput("hello");

		generator = (IOutputGenerator) context
				.getBean("CsvoutputGeneratorbean");
		generator.generateOutput("again");
	}
}
