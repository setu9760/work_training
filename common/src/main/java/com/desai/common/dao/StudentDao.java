package com.desai.common.dao;

import java.util.List;

import com.desai.common.pojo.Student;
import com.desai.common.pojo.Subject;

public interface StudentDao extends BaseDao {

	public int insert(Student student);

	public List<Subject> findAssociatedSubjects(int id);

}
