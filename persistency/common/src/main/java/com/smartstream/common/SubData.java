package com.smartstream.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sub_data")
public class SubData implements Serializable {

	private static final long serialVersionUID = -1393771514950894118L;

	@Column(name = "subDataString")
	private String subDataString;

	@Id
	@Column(name = "subDataNum")
	private int subDataNum;

	public SubData() {
	}

	public SubData(String subDataString, int subDataNum) {
		super();
		this.subDataString = subDataString;
		this.subDataNum = subDataNum;
	}

	/**
	 * @return the subDataString
	 */
	public String getSubDataString() {
		return subDataString;
	}

	/**
	 * @param subDataString
	 *            the subDataString to set
	 */
	public void setSubDataString(String subDataString) {
		this.subDataString = subDataString;
	}

	/**
	 * @return the subDataNum
	 */
	public int getSubDataNum() {
		return subDataNum;
	}

	/**
	 * @param subDataNum
	 *            the subDataNum to set
	 */
	public void setSubDataNum(int subDataNum) {
		this.subDataNum = subDataNum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubData [subDataString=");
		builder.append(subDataString);
		builder.append(", subDataNum=");
		builder.append(subDataNum);
		builder.append("]");
		return builder.toString();
	}

}
