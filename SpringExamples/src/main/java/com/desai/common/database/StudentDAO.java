package com.desai.common.database;

public interface StudentDAO {

	public void insert(Student student);

	public Student findByStudentById_Manual(int studId);

	public int countStudent();
}
