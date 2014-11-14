package spring.desai.dao;

import java.util.List;

import spring.desai.pojo.Student;
import spring.desai.pojo.Subject;


public interface StudentDao extends BaseDao {

	public int insert(Student student);

	public List<Student> findByName(String name);

	public List<Subject> findAssociatedSubjects(int id);
}
