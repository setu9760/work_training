package spring.desai.dao;

public interface BaseDao {

	public Object findById(int id);

	public int countAll();

	public void dropById(int id);
}
