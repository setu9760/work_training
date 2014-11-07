package com.desai.common.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.desai.common.config.DatabaseConfig;
import com.desai.common.database.Student;
import com.desai.common.database.StudentDAO;

public class App_database {

	// @Autowired
	static StudentDAO studentDAO;
	// @Autowired
	// @Qualifier("studentBean")
	static Student student;

	// @Autowired
	// @Qualifier("studentDao")
	// public static void setStudentDAO(StudentDAO studentDAO) {
	// App_database.studentDAO = studentDAO;
	// }
	//
	// @Autowired
	// @Qualifier("studentBean")
	// public static void setStudent(Student student) {
	// App_database.student = student;
	// }

	public static void main(String[] args) {
		ApplicationContext context;
		// = new ClassPathXmlApplicationContext("Spring-database.xml");

		context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
		studentDAO = (StudentDAO) context.getBean("studentDao");

		student = (Student) context.getBean("studentBean");
		// studentDAO.insert(student);

		Student student1 = studentDAO.findByStudentById_Manual(1);
		System.out.println(studentDAO.countStudent());
		try {
			studentDAO.insert(student);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(student1);
		System.out
				.println(studentDAO.findByStudentById_Manual(student.get_Id()));
	}
}
