package com;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.desai.java.Student;
import com.desai.java.Subject;
import com.desai.java.Tutor;
import com.desai.java.config.Config;
import com.desai.java.dao.StudentDao;
import com.desai.java.dao.SubjectDao;
import com.desai.java.dao.TutorDao;

public class Application {

	public static final Logger log = LogManager.getLogger(Application.class);

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Config.class);

		String[] beans = context.getBeanDefinitionNames();

		for (int i = 0; i < beans.length; i++) {
			System.out.println("bean: " + beans[i]);
		}

		Tutor tutor = (Tutor) context.getBean("tutorBean");
		TutorDao tutorDao = (TutorDao) context.getBean("tutorDao");

		Student student = (Student) context.getBean("studentBean");
		StudentDao studentDao = (StudentDao) context.getBean("studentDao");

		Subject subject = context.getBean(Subject.class);
		SubjectDao subjectDao = context.getBean(SubjectDao.class);

		tutorDao.insert(tutor);
		studentDao.insert(student);
		subjectDao.insert(subject);

		System.out.println(studentDao.findById(1));
		System.out.println(studentDao.findByName("test"));
		System.out.println(studentDao.countAll());

		System.out.println(tutorDao.findById(1));
		System.out.println(tutorDao.findByName("setu"));
		System.out.println(tutorDao.countAll());

		System.out.println(tutorDao.findSubjectOfTutor(1));

		System.out.println(subjectDao.findAllTutorsForSubject(1));

		context.registerShutdownHook();
		context.close();
	}
}
