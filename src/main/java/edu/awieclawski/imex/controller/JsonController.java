package edu.awieclawski.imex.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
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

	@RequestMapping(value = "/downloadjson", method = RequestMethod.GET)
	public void downloadJSON(HttpServletResponse response, @SessionAttribute("sessionValuesLists") ValuesLists valuesListsReceived) {
		String prettyValuesLists = null;
		ObjectMapper objectMapper = new ObjectMapper();

		response.setHeader("Content-Disposition", "attachment; filename=valueslists.json");

		if (valuesListsReceived != null) {

			try {
				prettyValuesLists = objectMapper.writerWithDefaultPrettyPrinter()
						.writeValueAsString(valuesListsReceived);
			} catch (JsonProcessingException e) {
				LOGGER.log(Level.SEVERE, "JsonProcessingException fault: " + prettyValuesLists, e);
			}

			try {
				InputStream is = new ByteArrayInputStream(prettyValuesLists.getBytes());
				IOUtils.copy(is, response.getOutputStream());
				response.flushBuffer();
			} catch (IOException ex) {
				LOGGER.log(Level.SEVERE, "Error writing file to output stream." + prettyValuesLists, ex);
			}
		}

	}

}
