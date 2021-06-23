package edu.awieclawski.imex.controller;

import java.io.ByteArrayInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.awieclawski.models.ValuesLists;

@Controller
public class JsonController {

	private final static Logger LOGGER = Logger.getLogger(JsonController.class.getName());

	@RequestMapping(value = "/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ValuesLists getJson(@SessionAttribute("sessionValuesLists") ValuesLists valuesListsReceived) {
		return valuesListsReceived;
	}

	@RequestMapping(value = "/downloadjson", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<InputStreamResource> downloadJSON(
			@SessionAttribute("sessionValuesLists") ValuesLists valuesListsReceived) {
		ObjectMapper mapper = new ObjectMapper();
		byte[] byteBuffer = null;

		try {
			byteBuffer = mapper.writeValueAsBytes(valuesListsReceived);
		} catch (JsonProcessingException e) {
			LOGGER.log(Level.SEVERE, "JsonProcessingException fault: " + valuesListsReceived, e);
		}

		return ResponseEntity.ok().contentLength(byteBuffer.length)
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header("Content-Disposition", "attachment; filename=\"valueslists.json\"")
				.body(new InputStreamResource(new ByteArrayInputStream(byteBuffer)));
	}
	
	@RequestMapping(value = "/downloadprettyjson", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<InputStreamResource> downloadPrettyJSON(
			@SessionAttribute("sessionValuesLists") ValuesLists valuesListsReceived) {
		ObjectMapper mapper = new ObjectMapper();
		byte[] byteBuffer = null;

		try {
			byteBuffer = mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(valuesListsReceived);
		} catch (JsonProcessingException e) {
			LOGGER.log(Level.SEVERE, "JsonProcessingException fault: " + valuesListsReceived, e);
		}

		return ResponseEntity.ok().contentLength(byteBuffer.length)
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header("Content-Disposition", "attachment; filename=\"prettyvalueslists.json\"")
				.body(new InputStreamResource(new ByteArrayInputStream(byteBuffer)));
	}

}
