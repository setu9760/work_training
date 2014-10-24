package com.desai.common.main;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.desai.common.database_advanced.JdbcStudentDAO;
import com.desai.common.database_advanced.Student;

public class App_database_advanced {
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-database.xml");
		JdbcStudentDAO jdbcStudentDAO = (JdbcStudentDAO) context
				.getBean("studentDAO");
		Student student = jdbcStudentDAO.findStudentById_Normal(1);
		System.out.println(student);

		student = jdbcStudentDAO.findStudentById_BeanRowMapper(2);
		System.out.println(student);

		student = (Student) context.getBean("studentBean");
		// jdbcStudentDAO.insert(student);

		System.out.println(jdbcStudentDAO.countStudent());
		System.out.println(jdbcStudentDAO.getAll_Normal());
		System.out.println(jdbcStudentDAO.getAll_BeanRowMapper());

	}
}
