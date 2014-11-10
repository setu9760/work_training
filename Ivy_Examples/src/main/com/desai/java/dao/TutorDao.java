package main.com.desai.java.dao;

import java.util.List;

import main.com.desai.java.Student;
import main.com.desai.java.Tutor;

public interface TutorDao extends BaseDao {

	public void insert(Tutor tutor);
	
	public List<Student> findAllStudents(int id);
}
