package spring.desai.dao;

import spring.desai.pojo.Subject;
import spring.desai.pojo.Tutor;

public interface TutorDao extends BaseDao {

	public void insert(Tutor tutor);

	public Subject findSubjectOfTutor(String tutor_id);

	public void dropAllTutorsForSubject(String subject_id);
}
