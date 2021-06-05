package edu.awieclawski.imex.service;

import java.io.BufferedReader;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.awieclawski.models.Value;

public interface I_ConversionService {

	Boolean operateLinesFromFile(BufferedReader reader);

	List<List<Value>> operateUploadedDataFile(MultipartFile file);

	BufferedReader convertMultipartFileToBufferedReader(MultipartFile file);

}
