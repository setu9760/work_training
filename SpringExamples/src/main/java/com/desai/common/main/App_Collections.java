package main.java.com.desai.common.main;

import main.java.com.desai.common.collections.All_Collections;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author desai
 * 
 */
public class App_Collections {
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"main/resources/Spring-Collections.xml");
		All_Collections all = (All_Collections) context
				.getBean("collection_bean");

		System.out.println(all);
		context.registerShutdownHook();
	}
}
