package com.desai.common.database_advanced;

public interface StudentDAO {

	public void insert(Student student);

	public Student findStudentById_Manual(int studId);

	public Student findStudentById_BeanRowMapper(int studId);

	public int countStudent();
}
