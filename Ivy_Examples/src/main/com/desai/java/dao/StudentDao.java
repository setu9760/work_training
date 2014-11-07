package main.com.desai.java.dao;

import main.com.desai.java.Student;

public interface StudentDao {

	public void insert(Student student);

	public Student findByStudentById_Manual(int studId);

	public int countStudent();

}
