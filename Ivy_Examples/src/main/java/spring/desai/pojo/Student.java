package spring.desai.pojo;

import spring.desai.utils.GuidGenerator;
import spring.desai.utils.GuidGeneratorException;

public class Student {

	private String student_id;
	private String student_name;
	private int student_age;

	public Student() throws GuidGeneratorException {
		this("", 18);
	}

	public Student(String name, int age) throws GuidGeneratorException {
		this(GuidGenerator.getInstance().getGuid(), name, age);
	}

	private Student(String id, String name, int age) {
		this.student_id = id;
		this.student_name = name;
		this.student_age = age;
	}

	/**
	 * @return the student_id
	 */
	public String getStudent_id() {
		return student_id;
	}

	/**
	 * @param student_id
	 *            the student_id to set
	 */
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	/**
	 * @return the student_name
	 */
	public String getStudent_name() {
		return student_name;
	}

	/**
	 * @param student_name
	 *            the student_name to set
	 */
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	/**
	 * @return the student_age
	 */
	public int getStudent_age() {
		return student_age;
	}

	/**
	 * @param student_age
	 *            the student_age to set
	 */
	public void setStudent_age(int student_age) {
		this.student_age = student_age;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + student_age;
		result = prime * result
				+ ((student_id == null) ? 0 : student_id.hashCode());
		result = prime * result
				+ ((student_name == null) ? 0 : student_name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Student)) {
			return false;
		}
		Student other = (Student) obj;
		if (student_age != other.student_age) {
			return false;
		}
		if (student_id == null) {
			if (other.student_id != null) {
				return false;
			}
		} else if (!student_id.equals(other.student_id)) {
			return false;
		}
		if (student_name == null) {
			if (other.student_name != null) {
				return false;
			}
		} else if (!student_name.equals(other.student_name)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [student_id=");
		builder.append(student_id);
		builder.append(", student_name=");
		builder.append(student_name);
		builder.append(", student_age=");
		builder.append(student_age);
		builder.append("]");
		return builder.toString();
	}

}
