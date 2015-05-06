package com.desai.common.pojo;

import java.io.Serializable;

import com.desai.common.utils.GuidGenerator;
import com.desai.common.utils.GuidGeneratorException;


public class Tutor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7912941565632566952L;
	private String tutor_id;
	private String tutor_name;
	private Subject subject;

	public Tutor() throws GuidGeneratorException {
		this("", new Subject());
	}

	public Tutor(String name, Subject subject) throws GuidGeneratorException {
		this(GuidGenerator.getInstance().getGuid(), name, subject);
	}

	private Tutor(String id, String name, Subject subject) {
		this.tutor_id = id;
		this.tutor_name = name;
		this.subject = subject;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return tutor_id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.tutor_id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return tutor_name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.tutor_name = name;
	}

	/**
	 * @return the subject
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tutor [id=");
		builder.append(tutor_id);
		builder.append(", name=");
		builder.append(tutor_name);
		builder.append(", ");
		builder.append(subject);
		builder.append("]");
		return builder.toString();
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
		result = prime * result + ((tutor_id == null) ? 0 : tutor_id.hashCode());
		result = prime * result + ((tutor_name == null) ? 0 : tutor_name.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		if (!(obj instanceof Tutor)) {
			return false;
		}
		Tutor other = (Tutor) obj;
		if (tutor_id == null) {
			if (other.tutor_id != null) {
				return false;
			}
		} else if (!tutor_id.equals(other.tutor_id)) {
			return false;
		}
		if (tutor_name == null) {
			if (other.tutor_name != null) {
				return false;
			}
		} else if (!tutor_name.equals(other.tutor_name)) {
			return false;
		}
		if (subject == null) {
			if (other.subject != null) {
				return false;
			}
		} else if (!subject.equals(other.subject)) {
			return false;
		}
		return true;
	}

}
