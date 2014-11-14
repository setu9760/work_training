package spring.desai.dao;

import java.util.List;

import spring.desai.pojo.Subject;
import spring.desai.pojo.Tutor;


public interface TutorDao extends BaseDao {

	public void insert(Tutor tutor);

	public List<Tutor> findByName(String name);

	public Subject findSubjectOfTutor(int tutor_id);

	public void dropAllTutorsForSubject(int subject_id);
}
