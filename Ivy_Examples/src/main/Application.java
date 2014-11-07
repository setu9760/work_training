package main;

import main.com.desai.config.Config;
import main.com.desai.java.Student;
import main.com.desai.java.Tutor;
import main.com.desai.java.dao.StudentDao;
import main.com.desai.java.dao.TutorDao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				Config.class);

		String[] beans = context.getBeanDefinitionNames();

		for (int i = 0; i < beans.length; i++) {
			System.out.println("bean: " + beans[i]);
		}

		Tutor tutor = (Tutor) context.getBean("tutorBean");
		TutorDao tutorDao = (TutorDao) context.getBean("tutorDao");

		tutorDao.insert(tutor);

		//
		// StudentDao dao = (StudentDao) context.getBean("");
	}

}
