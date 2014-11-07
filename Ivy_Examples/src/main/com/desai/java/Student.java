package main.com.desai.java;


public class Student {
	private int _Id;
	private String _Name;
	private int _age;

	public Student() {
	}

	public Student(int _Id, String _Name, int _age) {
		this._Id = _Id;
		this._Name = _Name;
		this._age = _age;
	}

	public int get_Id() {
		return _Id;
	}

	public void set_Id(int _Id) {
		this._Id = _Id;
	}

	public String get_Name() {
		return _Name;
	}

	public void set_Name(String _Name) {
		this._Name = _Name;
	}

	public int get_age() {
		return _age;
	}

	public void set_age(int _age) {
		this._age = _age;
	}

	@Override
	public String toString() {
		return "Student [_Id=" + _Id + ", _Name=" + _Name + ", _age=" + _age
				+ "]";
	}

}
