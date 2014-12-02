package spring.desai.dao;

import java.util.List;

public interface BaseDao {

	public Object findById(int id);

	public int countAll();

	public void dropById(int id);

	public List<? extends Object> getAll();
}
