package com.desai.common.dao;

import com.desai.common.pojo.Subject;
import com.desai.common.pojo.Tutor;

public interface TutorDao extends BaseDao {

	public void insert(Tutor tutor);

	public Subject findSubjectOfTutor(String tutor_id);

	public void dropAllTutorsForSubject(String subject_id);
}
