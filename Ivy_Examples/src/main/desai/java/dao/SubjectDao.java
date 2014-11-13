package desai.java.dao;

import java.util.List;

import desai.java.Subject;
import desai.java.Tutor;


public interface SubjectDao extends BaseDao {

	public void insert(Subject subject);

	public Subject findByName(String name);

	public List<Tutor> findAllTutorsForSubject(int subject_id);

}
