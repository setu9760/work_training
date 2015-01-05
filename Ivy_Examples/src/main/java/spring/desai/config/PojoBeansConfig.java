package spring.desai.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.desai.pojo.Student;
import spring.desai.pojo.Subject;
import spring.desai.pojo.Tutor;
import spring.desai.utils.GuidGenerator;
import spring.desai.utils.GuidGeneratorException;

@Configuration
public class PojoBeansConfig {

	// @Bean(name = "studentBean")
	// public Student getStudentBean() {
	// try {
	// return new Student("student 1", 20);
	// } catch (GuidGeneratorException e) {
	// return null;
	// }
	//
	// }
	//
	// @Bean(name = "tutorBean")
	// public Tutor getTutorBean() {
	// Tutor tutor = new Tutor();
	// tutor.setId("1");
	// tutor.setName("tutor 1");
	// tutor.setSubject(getSubjectBean());
	// return tutor;
	// }
	//
	// @Bean(name = "subjectBean")
	// public Subject getSubjectBean() {
	// Subject subject = new Subject();
	// subject.setSubject_id("1");
	// subject.setSubject_name("subject 1");
	// return subject;
	// }

	// @Bean(name = "studentBean2")
	// public Student getStudentBean2() {
	// Student student = new Student();
	// student.setId("2");
	// student.setName("student 2");
	// student.setAge(36);
	// return student;
	// }
	//
	// @Bean(name = "tutorBean2")
	// public Tutor getTutorBean2() {
	// Tutor tutor = new Tutor();
	// tutor.setId(2);
	// tutor.setName("tutor 2");
	// tutor.setSubject(getSubjectBean2());
	// return tutor;
	// }
	//
	// @Bean(name = "subjectBean2")
	// public Subject getSubjectBean2() {
	// Subject subject = new Subject();
	// subject.setSubject_id(2);
	// subject.setSubject_name("subject 2");
	// return subject;
	// }

}
