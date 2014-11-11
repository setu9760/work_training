package com.desai.java.dao;

import java.util.List;

import com.desai.java.Subject;
import com.desai.java.Tutor;

public interface SubjectDao extends BaseDao {

	public void insert(Subject subject);

	public Subject findByName(String name);

	public List<Tutor> findAllTutorsForSubject(int subject_id);

}
