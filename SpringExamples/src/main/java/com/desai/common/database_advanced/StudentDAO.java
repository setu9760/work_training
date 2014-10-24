package com.desai.common.database_advanced;

import java.util.List;

public interface StudentDAO {

	public void insert(Student student);

	public Student findStudentById_Normal(int studId);

	public Student findStudentById_BeanRowMapper(int studId);

	public void insertBatch(List<Student> students);

	public List<Student> getAll_Normal();

	public List<Student> getAll_BeanRowMapper();

	public int countStudent();
}
