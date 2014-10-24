package com.desai.common.main;

import com.desai.common.config.NumberConfig;
import com.desai.common.singleton.Number;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 */
public class App {
	/**
	 * This main is used for initial and singleton examples. For any other
	 * package examples refer to other main files
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * Different ways to define the beans scope
		 * 
		 * 1. Via xml file just declare the bean as usual with scope attribute
		 * set to "singleton", "prototype", etc.
		 * 
		 * NOTE: retrieve the context from Spring.xml config file
		 * 
		 * 
		 * 2. Via java config using annotations. Just declare the beans as usual
		 * in java config file and add two annotation to it first is
		 * Bean(name="beanName") and second is Scope("BeanScope")
		 * 
		 * NOTE: retrieve the context from java-config.class file
		 * 
		 * 
		 * 3. Directly adding annotation to the bean class add two annotation;
		 * Service("beanName") and Scope("beanScope").
		 * 
		 * NOTE: retrieve the context directly from bean.class
		 */

		ApplicationContext context;
		// = new AnnotationConfigApplicationContext( NumberConfig.class);
		context = new ClassPathXmlApplicationContext("Spring-Singleton.xml");

		Number number = (Number) context.getBean("numberBean");
		number.printnumber_incr();
		number.printnumber_incr();
		number.printnumber_incr();
		number.printnumber_incr();

		Number number2 = (Number) context.getBean("numberBean");
		number2.printnumber_incr();
		number2.printnumber_incr();
		// Things things = (Things) context.getBean("ThingsBean");
		// Person person = (Person) context.getBean("personBean2");
		// System.out.println(person);
		//
		// person = (Person) context.getBean("personBean1");
		//
		// System.out.println(things);

		/**
		 * ################################################################## //
		 * Getting context by using Spring-Common.xml file //
		 * 
		 * ApplicationContext context = new ClassPathXmlApplicationContext(
		 * "Spring-Common.xml"); OutputHelper outputHelper = (OutputHelper)
		 * context .getBean("OutputHelper");
		 * outputHelper.getiOutputGenerator().generateOutput("one more exp");
		 */

		/**
		 * ################################################################## //
		 * Getting context by using multiple config classes.//
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
