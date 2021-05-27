package edu.awieclawski.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Value implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1256585449066627440L;
	List<SubValue> subValues;
	Integer valueCounter;

	public Value(List<SubValue> subValues, Integer valueCounter) {
		this(subValues);
		this.valueCounter = valueCounter;
	}

	public Value(List<SubValue> subValues) {
		this.subValues = subValues;
	}

	public Value() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<SubValue> addSubValues(SubValue subValue) {
		List<SubValue> subValueList = getSubValues();
		if (subValueList == null) {
			setSubValues(new ArrayList<SubValue>());
		}
		subValues.add(subValue);

		return subValues;
	}

	public List<SubValue> getSubValues() {
		return subValues;
	}

	public void setSubValues(List<SubValue> subValues) {
		this.subValues = subValues;
	}

	public Integer getValueCounter() {
		return valueCounter;
	}

	public void setValueCounter(Integer valueCounter) {
		this.valueCounter = valueCounter;
	}

	@Override
	public String toString() {
		return "Value [valueCounter=" + getValueCounter() + ",subValues=" + subValues + "]";
	}

}
