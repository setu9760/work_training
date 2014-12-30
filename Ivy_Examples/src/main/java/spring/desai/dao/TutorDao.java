package spring.desai.dao;

import spring.desai.pojo.Subject;
import spring.desai.pojo.Tutor;

public interface TutorDao extends BaseDao {

	public void insert(Tutor tutor);

	public Subject findSubjectOfTutor(int tutor_id);

	public void dropAllTutorsForSubject(int subject_id);
}
