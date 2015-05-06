package com.desai.common.dao;

import java.util.List;

import com.desai.common.pojo.Subject;
import com.desai.common.pojo.Tutor;

public interface SubjectDao extends BaseDao {

	public void insert(Subject subject);

	public List<Tutor> findAllTutorsForSubject(int subject_id);

}
