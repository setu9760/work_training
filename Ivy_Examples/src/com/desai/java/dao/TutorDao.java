package com.desai.java.dao;

import java.util.List;

import com.desai.java.Student;
import com.desai.java.Tutor;


public interface TutorDao extends BaseDao {

	public void insert(Tutor tutor);
	
	public List<Student> findAllStudents(int id);
}
