package main.com.desai.java.dao;

import main.com.desai.java.Tutor;

public interface TutorDao {

	public void insert(Tutor tutor);

	public Tutor findTutorById(int tutor_id);

	public Tutor findTutorByName(String name);

	public int countTutors();

}
