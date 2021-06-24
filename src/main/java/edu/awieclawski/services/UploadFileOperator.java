package edu.awieclawski.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import edu.awieclawski.imex.exceptions.NullPathVariableException;
import edu.awieclawski.imex.exceptions.StorageException;
//import edu.awieclawski.models.Value;
import edu.awieclawski.models.ValuesLists;
import edu.awieclawski.utils.CsvReader;

@Component
public class UploadFileOperator {

	private final static Logger LOGGER = Logger.getLogger(UploadFileOperator.class.getName());

	private final String SKIP_DELIMITER = "#";
	private CsvReader csvR = new CsvReader();

	public ValuesLists uploadedFileOperate(MultipartFile file) throws NullPathVariableException {
		ValuesLists m_result = new ValuesLists();
		
		if (file.isEmpty()) {
			throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
		}

		BufferedReader reader = null;
		InputStream is = null;
		try {
			is = file.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is));
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "IOException fault: " + reader, e);
		}

		try {
			while (reader.ready()) {
				String line = reader.readLine();

				if (line.startsWith(SKIP_DELIMITER)) {
					continue;
				}
				m_result.addValuesList(csvR.lineOperate(line)); // building the result list

			}
		} catch (IOException e1) {
			LOGGER.log(Level.SEVERE, "BufferedReader readLine fault: " + reader, e1);
		}
		try {
			reader.close();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "IOException close fault: " + reader, e);
		}

//		if (m_result != null)
//			LOGGER.log(Level.INFO, "m_result:" + m_result.toString());

		return m_result;
	}

}
