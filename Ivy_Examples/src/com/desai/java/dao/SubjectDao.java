package com.desai.java.dao;

import com.desai.java.Subject;

public interface SubjectDao extends BaseDao {

	public void insert(Subject subject);

	public Subject findByName(String name);

}
