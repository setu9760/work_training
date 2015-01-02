package spring.desai.dao;

import java.util.List;

public interface BaseDao {

	public Object findById(String id);

	public List<?> findByName(String name);

	public int countAll();

	public void dropById(String id);

	public List<?> getAll();

}
