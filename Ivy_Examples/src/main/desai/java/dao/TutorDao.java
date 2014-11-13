package desai.java.dao;

import java.util.List;

import desai.java.Subject;
import desai.java.Tutor;

public interface TutorDao extends BaseDao {

	public void insert(Tutor tutor);

	public List<Tutor> findByName(String name);

	public Subject findSubjectOfTutor(int tutor_id);

	public void dropAllTutorsForSubject(int subject_id);
}
