package com.desai.java.dao;

import java.util.List;

import com.desai.java.Student;
import com.desai.java.Subject;


public interface StudentDao extends BaseDao {

	public void insert(Student student);
	
	public List<Subject> findAssociatedSubjects(int id);
}
