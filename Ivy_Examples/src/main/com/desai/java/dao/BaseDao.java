package main.com.desai.java.dao;

public interface BaseDao {

	public Object findById(int id);

	public Object findByName(String name);

	public int countAll();
	
	public boolean dropAll();
}
