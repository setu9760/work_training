package desai.java.dao;

import java.util.List;

import desai.java.Student;
import desai.java.Subject;

public interface StudentDao extends BaseDao {

	public int insert(Student student);

	public List<Student> findByName(String name);

	public List<Subject> findAssociatedSubjects(int id);
}
