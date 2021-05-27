package edu.awieclawski.imex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.awieclawski.models.SubValue;
import edu.awieclawski.models.Value;

public class DataImport {

	private final static Logger LOGGER = Logger.getLogger(DataImport.class.getName());

	private final String SUBVALUES_DELIMITER = ",";
	private final String VALUES_DELIMITER = ";";
	private final String SKIP_DELIMITER = "#";
	// mark resources directory as 'Use as Source Folder' in Eclipse
	private final String filePath = "data.txt";
	private List<List<Value>> m_result = new ArrayList<>();

	public List<List<Value>> operateDataFile() {

		if (!operateLinesFromFile(filePath))
			LOGGER.log(Level.SEVERE, "BufferedReader fault from: " + filePath);

		if (m_result != null)
			LOGGER.log(Level.INFO, "m_result:" + m_result.toString());

		return m_result;
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

	/**
	 * returns true if no error
	 * 
	 * @param path
	 * @return
	 */
	private Boolean operateLinesFromFile(String path) {
		Boolean result = false;
		BufferedReader reader = null;

		URLConnection urlConnection = getURLConnection(path);

		try {
			if (urlConnection != null)
				reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			else
				LOGGER.log(Level.SEVERE, " not found urlConnection: " + urlConnection);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "IOException fault: " + reader, e);
		}

		try {
			while (reader.ready()) {
				String line = reader.readLine();

				if (line.startsWith(SKIP_DELIMITER)) {
					continue;
				}
				m_result.add(getValuesFromLine(line)); // building the result list

			}
		} catch (IOException e1) {
			result = Boolean.valueOf(false);
			LOGGER.log(Level.SEVERE, "BufferedReader readLine fault: " + reader, e1);
		}
		result = Boolean.valueOf(true);
		try {
			reader.close();
		} catch (IOException e) {
			result = Boolean.valueOf(false);
			LOGGER.log(Level.SEVERE, "IOException close fault: " + reader, e);
		}
		return result;
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	private URLConnection getURLConnection(String path) {
		URLConnection urlConnection = null;
		if (path != null) {
			try {
				URL url = this.getClass().getClassLoader().getResource(path);
				if (url != null)
					urlConnection = this.getClass().getClassLoader().getResource(path).openConnection();
				else
					LOGGER.log(Level.SEVERE, " not found url:" + url);
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "IOException fault: " + urlConnection, e);
			}
			if (urlConnection != null)
				urlConnection.setUseCaches(false);
		}
		return urlConnection;
	}

}
