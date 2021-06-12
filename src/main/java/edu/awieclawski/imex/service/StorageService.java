package edu.awieclawski.imex.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

//import edu.awieclawski.models.Value;
import edu.awieclawski.models.ValuesLists;

import java.nio.file.Path;
//import java.util.List;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	void store(MultipartFile file);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();
	
	ValuesLists fileOperate(MultipartFile file);
	
}
