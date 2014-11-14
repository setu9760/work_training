package spring.desai;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.apache.commons.logging.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.desai.config.Config;
import spring.desai.dao.StudentDao;
import spring.desai.dao.SubjectDao;
import spring.desai.dao.TutorDao;
import spring.desai.pojo.Student;
import spring.desai.pojo.Subject;
import spring.desai.pojo.Tutor;

public class Application {

	public static final Logger log = LogManager.getLogger("mainAppLogger");

	// public static final Log log = LogFactory.getLog("mainAppLogger");

	// public static final org.apache.logging.log4j.Logger log4j2 =
	// org.apache.logging.log4j.LogManager
	// .getLogger("");

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Config.class);

		String[] beans = context.getBeanDefinitionNames();

		for (int i = 0; i < beans.length; i++) {
			System.out.println("bean: " + beans[i]);
			log.info("bean: " + beans[i]);
			// log4j2.info("log4j2 info");
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

		subjectDao.insert(subject);
		subjectDao.insert(subject2);

		tutorDao.insert(tutor);
		tutorDao.insert(tutor2);

		studentDao.insert(student);
		studentDao.insert(student2);

		System.out.println(studentDao.findById(1));
		System.out.println(studentDao.findByName("student 1"));
		System.out.println(studentDao.countAll());
		System.out.println(studentDao.findAssociatedSubjects(1));

		System.out.println(tutorDao.findById(1));
		System.out.println(tutorDao.findByName("tutor 2"));
		System.out.println(tutorDao.countAll());

		System.out.println(tutorDao.findSubjectOfTutor(6));

		System.out.println(subjectDao.findAllTutorsForSubject(1));

		System.out.println(subjectDao.findById(1));

		// subjectDao.dropById(2);
		// studentDao.dropById(2);
		// tutorDao.dropById(3);

		if (log.isInfoEnabled())
			log.info("info");
		if (log.isDebugEnabled())
			log.debug("debug", new RuntimeException());
		if (log.isEnabledFor(Level.ERROR))
			log.error("error", new RuntimeException());
		if (log.isEnabledFor(Level.FATAL))
			log.fatal("fatal", new RuntimeException());
		if (log.isTraceEnabled())
			log.trace("trace");

		context.registerShutdownHook();
		context.close();
	}
}
