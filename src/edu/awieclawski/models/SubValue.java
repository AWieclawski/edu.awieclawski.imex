package edu.awieclawski.models;

import java.io.Serializable;

public class SubValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3146166026065085718L;
	String subValue;
	Integer subValueCounter;

	public SubValue(String subValue, Integer subValueCounter) {
		this(subValue);
		this.subValueCounter = subValueCounter;
	}

	public SubValue(String subValue) {
		this.subValue = subValue;
	}

	public SubValue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSubValue() {
		return subValue;
	}

	public void setSubValue(String subValue) {
		this.subValue = subValue;
	}

	public Integer getSubValueCounter() {
		return subValueCounter;
	}

	public void setSubValueCounter(Integer subValueCounter) {
		this.subValueCounter = subValueCounter;
	}

	@Override
	public String toString() {
		return "SubValue [subValueCounter=" + getSubValueCounter() + ",subValue=" + subValue + "]";
	}

}
