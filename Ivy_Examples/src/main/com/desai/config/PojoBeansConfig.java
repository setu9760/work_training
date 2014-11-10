package main.com.desai.config;

import main.com.desai.java.Student;
import main.com.desai.java.Subject;
import main.com.desai.java.Tutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PojoBeansConfig {

	@Bean(name = "studentBean")
	public Student getStudentBean() {
		Student student = new Student();
		student.setId(1);
		student.setName("test");
		student.setAge(20);
		return student;
	}

	@Bean(name = "tutorBean")
	public Tutor getTutorBean() {
		Tutor tutor = new Tutor();
		tutor.setName("setu");
		tutor.setId(1);
		return tutor;
	}

	@Bean(name = "subjectBean")
	public Subject getSubjectBean() {
		Subject subject = new Subject();
		subject.setSubject_id(1);
		subject.setSubject_name("comp");
		return subject;
	}

}
