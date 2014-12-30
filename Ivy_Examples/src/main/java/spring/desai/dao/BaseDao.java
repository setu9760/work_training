package spring.desai.dao;

import java.util.List;

public interface BaseDao {

	public Object findById(int id);

	public List<?> findByName(String name);

	public int countAll();

	public void dropById(int id);

	public List<?> getAll();

}
