package spring.desai.dao;

import java.util.List;

public interface BaseDao {

	public abstract Object findById(int id);

	public abstract int countAll();

	public void dropById(int id);

	public List<?> getAll();

}
