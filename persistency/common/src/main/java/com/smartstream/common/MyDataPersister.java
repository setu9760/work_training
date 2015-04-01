package com.smartstream.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.gigaspaces.datasource.BulkDataPersister;
import com.gigaspaces.datasource.BulkItem;
import com.gigaspaces.datasource.DataIterator;
import com.gigaspaces.datasource.DataSourceException;
import com.gigaspaces.datasource.ManagedDataSource;

@SuppressWarnings({ "rawtypes", "deprecation" })
public class MyDataPersister extends JdbcDaoSupport implements
		BulkDataPersister, ManagedDataSource {

	String[] managedEntries;

	public MyDataPersister(String[] managedEntries, DataSource dataSource) {
		setDataSource(dataSource);
		this.managedEntries = managedEntries;
	}

	@Override
	public void executeBulk(List<BulkItem> paramList)
			throws DataSourceException {

		for (BulkItem bulkItem : paramList) {

			if (isManagedClass(bulkItem.getTypeName())) {

				if (bulkItem.getItem() instanceof Data) {
					Data data = (Data) bulkItem.getItem();

					switch (bulkItem.getOperation()) {
					case BulkItem.WRITE:
						executeWrite(data);
						break;
					case BulkItem.UPDATE:
						executeUpdateIfExists(data);
						break;
					case BulkItem.REMOVE:
						executeRemoveIfExists(data);
						break;
					case BulkItem.PARTIAL_UPDATE:
						executepartialUpdateIfExists(data);
						break;

					default:
						break;
					}
				}
			}
		}
	}

	private void executepartialUpdateIfExists(Data data) {
		if (existsInDatabase(data)) {
			String sql = "UPDATE data SET Object = ? where Id = ?";
			getJdbcTemplate().update(sql, new Object[] { data, data.getId() });
		}
	}

	private void executeRemoveIfExists(Data data) {
		if (existsInDatabase(data)) {
			String sql = "DELETE FROM data WHERE Id = ?";
			getJdbcTemplate().update(sql, new Object[] { data.getId() });
		}

	}

	private void executeUpdateIfExists(Data data) {
		if (existsInDatabase(data)) {
			String sql = "UPDATE data SET Object = ? where Id = ?";
			getJdbcTemplate().update(sql, new Object[] { data, data.getId() });
		}
	}

	private void executeWrite(Data data) {
		if (!existsInDatabase(data)) {
			String sql = "INSERT INTO data (Id, obj_name, Object) VALUES (?, ?, ?)";
			getJdbcTemplate().update(
					sql,
					new Object[] { data.getId(), data.getClass().getName(),
							data });
		}

	}

	private boolean existsInDatabase(Data data) {
		String sql = "select count(*) from data where Id = ?";
		int count = getJdbcTemplate().queryForObject(sql,
				new Object[] { data.getId() }, Integer.class);
		if (count == 1)
			return true;
		return false;
	}

	/*
	 * Checks if the entry class needs to be managed of not
	 */
	private boolean isManagedClass(String string) {
		if (null != string)
			if (Arrays.asList(managedEntries).contains(string))
				return true;
		return false;
	}

	@Override
	public void init(Properties paramProperties) throws DataSourceException {
		// TODO Auto-generated method stub

	}

	@Override
	public DataIterator initialLoad() throws DataSourceException {
		List<Data> list = new ArrayList<Data>();
		String sql = "SELECT * FROM data";
		list = getJdbcTemplate().query(sql, new RowMapper<Data>() {

			@Override
			public Data mapRow(ResultSet resultSet, int rowNum)
					throws SQLException {
				Data data = null;
				Blob blob = resultSet.getBlob("Object");
				int length = (int) blob.length();
				byte[] bytes = blob.getBytes(0, length);
				ObjectInputStream objectIn = null;
				try {
					objectIn = new ObjectInputStream(new ByteArrayInputStream(
							bytes));
					data = (Data) objectIn.readObject();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return data;
			}
		});
		return (DataIterator) list.iterator();
	}

	@Override
	public void shutdown() throws DataSourceException {
		// TODO Auto-generated method stub

	}

}
