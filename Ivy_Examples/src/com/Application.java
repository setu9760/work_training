package com;

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

		Subject subject = (Subject) context.getBean("subjectBean");
		SubjectDao subjectDao = (SubjectDao) context.getBean("subjectDao");

		Tutor tutor2 = (Tutor) context.getBean("tutorBean2");

		Student student2 = (Student) context.getBean("studentBean2");

		Subject subject2 = (Subject) context.getBean("subjectBean2");

		// subjectDao.insert(subject);
		// subjectDao.insert(subject2);
		//
		// tutorDao.insert(tutor);
		// tutorDao.insert(tutor2);
		//
		// studentDao.insert(student);
		// studentDao.insert(student2);

		System.out.println(studentDao.findById(1));
		System.out.println(studentDao.findByName("student 1"));
		System.out.println(studentDao.countAll());

		System.out.println(tutorDao.findById(1));
		System.out.println(tutorDao.findByName("tutor 2"));
		System.out.println(tutorDao.countAll());

		System.out.println(tutorDao.findSubjectOfTutor(6));

		System.out.println(subjectDao.findAllTutorsForSubject(1));

		System.out.println(subjectDao.findById(1));

		subjectDao.dropById(2);
		studentDao.dropById(2);
		tutorDao.dropById(3);

		context.registerShutdownHook();
		context.close();
	}
}
