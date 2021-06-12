package edu.awieclawski.imex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import edu.awieclawski.imex.exception.StorageException;
import edu.awieclawski.imex.exception.StorageFileNotFoundException;
import edu.awieclawski.imex.properties.StorageProperties;
//import edu.awieclawski.models.Value;
import edu.awieclawski.models.ValuesLists;
import edu.awieclawski.services.UploadFileOperator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

	private final static Logger LOGGER = Logger.getLogger(FileSystemStorageService.class.getName());

	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void store(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

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
