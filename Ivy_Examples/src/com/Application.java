package com;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.desai.java.Student;
import com.desai.java.Subject;
import com.desai.java.Tutor;
import com.desai.java.config.ConfigTest;
import com.desai.java.dao.StudentDao;
import com.desai.java.dao.SubjectDao;
import com.desai.java.dao.TutorDao;

public class Application {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				ConfigTest.class);

		String[] beans = context.getBeanDefinitionNames();

		for (int i = 0; i < beans.length; i++) {
			System.out.println("bean: " + beans[i]);
		}

		Tutor tutor = (Tutor) context.getBean("tutorBean");
		TutorDao tutorDao = (TutorDao) context.getBean("tutorDao");

		Student student = (Student) context.getBean("studentBean");
		StudentDao studentDao = (StudentDao) context.getBean("studentDao");

		Subject subject = (Subject) context.getBean(Subject.class);
		SubjectDao subjectDao = (SubjectDao) context.getBean(SubjectDao.class);

		// tutorDao.insert(tutor);
		// studentDao.insert(student);
		// subjectDao.insert(subject);
		System.out.println(tutorDao.findById(1));
		System.out.println(tutorDao.findByName("setu"));
		System.out.println(tutorDao.countAll());
		// StudentDao dao = (StudentDao) context.getBean("");

		List<Subject> subjects = studentDao.findAssociatedSubjects(1);
		for (Subject subject1 : subjects) {
			System.out.println(subject1);
		}
	}
}
