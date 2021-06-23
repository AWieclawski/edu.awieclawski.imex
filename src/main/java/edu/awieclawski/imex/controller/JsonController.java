package edu.awieclawski.imex.controller;

import java.io.ByteArrayInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value = "/downloadjson/{subPage}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<InputStreamResource> downloadJSONsub(
			@PathVariable(value = "subPage", required = false) String subPageName,
			@SessionAttribute("sessionValuesLists") ValuesLists valuesListsReceived) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		byte[] byteBuffer = null;

		try {
			if (subPageName.equals("pretty"))
				byteBuffer = mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(valuesListsReceived);
			else if (subPageName.equals("simple"))
				byteBuffer = mapper.writeValueAsBytes(valuesListsReceived);
			else
				throw new Exception();
		} catch (JsonProcessingException e) {
			LOGGER.log(Level.SEVERE, "JsonProcessingException fault: " + valuesListsReceived, e);
		}

		return ResponseEntity.ok().contentLength(byteBuffer.length)
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header("Content-Disposition", "attachment; filename=\"valueslists.json\"")
				.body(new InputStreamResource(new ByteArrayInputStream(byteBuffer)));
	}

}
