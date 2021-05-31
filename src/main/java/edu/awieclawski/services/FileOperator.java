package edu.awieclawski.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.awieclawski.models.Value;
import edu.awieclawski.utils.CsvReader;

public class FileOperator {

	private final static Logger LOGGER = Logger.getLogger(FileOperator.class.getName());

	private final String SKIP_DELIMITER = "#";
	private List<List<Value>> m_result = new ArrayList<>();
	private CsvReader csvR = new CsvReader();

	/**
	 * 
	 * @param path
	 * @return
	 */
	public List<List<Value>> operateDataFileFromPath(String path) {

		if (!operateLinesFromFile(path))
			LOGGER.log(Level.SEVERE, "BufferedReader fault from: " + path);

		if (m_result != null)
			LOGGER.log(Level.INFO, "m_result:" + m_result.toString());

		return m_result;
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
				m_result.add(csvR.lineOperate(line)); // building the result list

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
