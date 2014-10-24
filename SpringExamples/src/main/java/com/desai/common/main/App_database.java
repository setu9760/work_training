package com.desai.common.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.desai.common.database.Student;
import com.desai.common.database.StudentDAO;

public class App_database {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-database.xml");

		StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
		Student student = (Student) context.getBean("studentBean");
		studentDAO.insert(student);

		Student student1 = studentDAO.findByStudentById_Manual(1);
		System.out.println(studentDAO.countStudent());
		System.out.println(student1);
	}

}
