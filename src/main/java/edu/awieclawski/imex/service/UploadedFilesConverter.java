package edu.awieclawski.imex.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.multipart.MultipartFile;

import edu.awieclawski.models.Value;
import edu.awieclawski.utils.CsvReader;

public class UploadedFilesConverter implements I_ConversionService {

	private final static Logger LOGGER = Logger.getLogger(UploadedFilesConverter.class.getName());

	private final String SKIP_DELIMITER = "#";
	private List<List<Value>> m_result = new ArrayList<>();
	private CsvReader csvR = new CsvReader();

	@Override
	public Boolean operateLinesFromFile(BufferedReader reader) {

		Boolean result = false;

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

	@Override
	public List<List<Value>> operateUploadedDataFile(MultipartFile file) {

		BufferedReader reader = null;
		if (file != null) {
			reader = convertMultipartFileToBufferedReader(file);

			if (!operateLinesFromFile(reader))
				LOGGER.log(Level.SEVERE, "BufferedReader fault from: " + reader);

			if (m_result != null)
				LOGGER.log(Level.INFO, "m_result:" + m_result.toString());
		}

		return m_result;

//		return null;
	}

	@Override
	public BufferedReader convertMultipartFileToBufferedReader(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

}
