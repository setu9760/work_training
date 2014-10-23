package com.desai.common.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.desai.common.collections.All_Collections;

/**
 * @author desai
 * 
 */
public class App_Collections {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-Collections.xml");
		All_Collections all = (All_Collections) context
				.getBean("collection_bean");

		System.out.println(all);

	}
}
