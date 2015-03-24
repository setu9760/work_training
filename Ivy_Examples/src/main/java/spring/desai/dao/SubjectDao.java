package spring.desai.dao;

import java.util.List;

import spring.desai.pojo.Subject;
import spring.desai.pojo.Tutor;

public interface SubjectDao extends BaseDao {

	public void insert(Subject subject);

	public List<Tutor> findAllTutorsForSubject(int subject_id);

}
