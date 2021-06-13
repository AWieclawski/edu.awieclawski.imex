package edu.awieclawski.imex.service;

import org.springframework.web.multipart.MultipartFile;

import edu.awieclawski.models.ValuesLists;

public interface I_UploadService {
	
	public ValuesLists fileOperate(MultipartFile file);

}
