package edu.awieclawski.imex.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.awieclawski.imex.exception.StorageException;
import edu.awieclawski.models.ValuesLists;
import edu.awieclawski.services.UploadFileOperator;

@Service
public class UploadService implements I_UploadService {

	private final static Logger LOGGER = Logger.getLogger(UploadService.class.getName());

	@Override
	public ValuesLists fileOperate(MultipartFile file) {
		ValuesLists valuesLists = new ValuesLists();

		AnnotationConfigApplicationContext csvCtx = new AnnotationConfigApplicationContext();
		csvCtx.scan("edu.awieclawski.utils", "edu.awieclawski.services");
		csvCtx.refresh();

		UploadFileOperator upFileOperator = csvCtx.getBean(UploadFileOperator.class);

		try {
			valuesLists = upFileOperator.uploadedFileOperate(file);
		} catch (StorageException e) {
			LOGGER.log(Level.SEVERE, "StorageException fault: " + e);
		}

//		if (valuesLists != null)
//			LOGGER.log(Level.INFO, "m_result=" + valuesLists.toString());

		csvCtx.close();
		return valuesLists;
	}

}
