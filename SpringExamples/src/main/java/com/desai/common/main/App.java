package com.desai.common.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.desai.common.OutputHelper;
import com.desai.common.config.AppConfig;
import com.desai.common.interfaces.IOutputGenerator;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-Common.xml");
		OutputHelper outputHelper = (OutputHelper) context
				.getBean("OutputHelper");
		outputHelper.getiOutputGenerator().generateOutput("one more exp");

		/**
		 * // Getting context by using multiple config classes.
		 * 
		 * ApplicationContext context = new AnnotationConfigApplicationContext(
		 * AppConfig.class); IOutputGenerator generator = (IOutputGenerator)
		 * context .getBean("JsonOutputGeneratorBean");
		 * generator.generateOutput("hello");
		 * 
		 * generator = (IOutputGenerator) context
		 * .getBean("CsvoutputGeneratorbean");
		 * generator.generateOutput("again");
		 **/
	}
}
