package com.desai.java.dao;

import java.util.List;

import com.desai.java.Subject;
import com.desai.java.Tutor;

public interface TutorDao extends BaseDao {

	public void insert(Tutor tutor);

	public List<Tutor> findByName(String name);

	public Subject findSubjectOfTutor(int tutor_id);
}
