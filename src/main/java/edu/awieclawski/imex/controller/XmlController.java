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
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import edu.awieclawski.models.ValuesLists;

@Controller
public class XmlController {

	private final static Logger LOGGER = Logger.getLogger(XmlController.class.getName());

	@RequestMapping(value = "/xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody ValuesLists getXml(@SessionAttribute("sessionValuesLists") ValuesLists valuesListsReceived) {
		return valuesListsReceived;
	}

	@RequestMapping(value = "/downloadxml", method = RequestMethod.GET, produces = "application/xml")
	public ResponseEntity<InputStreamResource> downloadXML(
			@SessionAttribute("sessionValuesLists") ValuesLists valuesListsReceived) {
		XmlMapper xmlMapper = new XmlMapper();
		byte[] byteBuffer = null;

		try {
			byteBuffer = xmlMapper.writeValueAsBytes(valuesListsReceived);
		} catch (JsonProcessingException e) {
			LOGGER.log(Level.SEVERE, "JsonProcessingException fault: " + valuesListsReceived, e);
		}

		return ResponseEntity.ok().contentLength(byteBuffer.length)
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header("Content-Disposition", "attachment; filename=\"valueslists.xml\"")
				.body(new InputStreamResource(new ByteArrayInputStream(byteBuffer)));
	}

	@RequestMapping(value = "/downloadprettyxml", method = RequestMethod.GET, produces = "application/xml")
	public ResponseEntity<InputStreamResource> downloadPrettyXML(
			@SessionAttribute("sessionValuesLists") ValuesLists valuesListsReceived) {
		XmlMapper xmlMapper = new XmlMapper();
		byte[] byteBuffer = null;
		
		try {
			byteBuffer = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(valuesListsReceived);
		} catch (JsonProcessingException e) {
			LOGGER.log(Level.SEVERE, "JsonProcessingException fault: " + valuesListsReceived, e);
		}
		
		return ResponseEntity.ok().contentLength(byteBuffer.length)
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header("Content-Disposition", "attachment; filename=\"prettyvalueslists.xml\"")
				.body(new InputStreamResource(new ByteArrayInputStream(byteBuffer)));
	}

}
