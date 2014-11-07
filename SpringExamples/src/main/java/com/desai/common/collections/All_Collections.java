package com.desai.common.collections;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class All_Collections {

	List<Object> list;
	Map<Object, Object> map;
	Set<Object> set;
	Properties properties;

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public Map<Object, Object> getMap() {
		return map;
	}

	public void setMap(Map<Object, Object> map) {
		this.map = map;
	}

	public Set<Object> getSet() {
		return set;
	}

	public void setSet(Set<Object> set) {
		this.set = set;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Entries in list \n");
		for (Object object : list) {
			sb.append(String.valueOf(object));
			sb.append("\n");
		}
		sb.append("\nEntries in map \n");
		for (Map.Entry<Object, Object> entry : map.entrySet()) {
			sb.append("key: " + entry.getKey() + " value: " + entry.getValue());
			sb.append("\n");
		}
		sb.append("\nEntries in the set \n");
		for (Object object : set) {
			sb.append(object);
			sb.append("\n");
		}
		sb.append("\nEntries in properties \n");
		for (Object key : properties.stringPropertyNames()) {
			sb.append(key + " : " + properties.getProperty((String) key));
			sb.append("\n");
		}
		return sb.toString();
	}
}
