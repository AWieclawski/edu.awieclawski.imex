package edu.awieclawski.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ValuesLists implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3389794206615784973L;
	List<List<Value>> valuesLists;

	
	
	public ValuesLists() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ValuesLists(List<List<Value>> valuesLists) {
		super();
		this.valuesLists = valuesLists;
	}

	public List<List<Value>> getValuesLists() {
		return valuesLists;
	}

	@XmlElement
	public void setValuesLists(List<List<Value>> valuesLists) {
		this.valuesLists = valuesLists;
	}
	
	public List<List<Value>> addValuesList(List<Value> valuesList) {
		List<List<Value>> listOfValueList = getValuesLists();
		if (listOfValueList == null) {
			setValuesLists(new ArrayList<List<Value>>());
		}
		valuesLists.add(valuesList);

		return valuesLists;
	}

	@Override
	public String toString() {
		return "ValuesLists [valuesLists=" + valuesLists + "]";
	}

}
