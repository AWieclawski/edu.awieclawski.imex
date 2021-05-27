package edu.awieclawski.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.awieclawski.models.SubValue;
import edu.awieclawski.models.Value;

public class CsvReader {

	private final String SUBVALUES_DELIMITER = ",";
	private final String VALUES_DELIMITER = ";";

	public List<Value> lineOperate(String line) {
		if (line != null)
			return getValuesFromLine(line);
		return null;
	}

	/**
	 * 
	 * @param line
	 * @return
	 */
	private List<Value> getValuesFromLine(String line) {
		int a_count = 1;
		List<Value> valuesList = new ArrayList<>();
		Value value = new Value();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(VALUES_DELIMITER);
			while (rowScanner.hasNext()) {
				String valueAsStr = rowScanner.next();
				value = getSubValuesFromValues(valueAsStr);
				valuesList.add(value);
				value.setValueCounter(a_count);
				a_count++;
			}
		}

//		if (valuesList != null)
//			LOGGER.log(Level.INFO, "-valuesList" + valuesList.toString());

		return valuesList;
	}

	/**
	 * 
	 * @param line
	 * @return
	 */
	private Value getSubValuesFromValues(String line) {
		int b_count = 1;
		Value newValue = new Value();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(SUBVALUES_DELIMITER);
			while (rowScanner.hasNext()) {
				SubValue newSubValue = new SubValue();
				String subValueAsStr = rowScanner.next();
				newSubValue.setSubValue(subValueAsStr);
				newValue.addSubValues(newSubValue);
				newSubValue.setSubValueCounter(b_count);
				b_count++;
			}
		}

//		if (newValue != null)
//			LOGGER.log(Level.INFO, "--newValue:" + newValue.toString());

		return newValue;
	}

}
