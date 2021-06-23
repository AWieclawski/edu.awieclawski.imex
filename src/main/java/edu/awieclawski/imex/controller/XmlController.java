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
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import edu.awieclawski.models.ValuesLists;

@Controller
public class XmlController {

	private final static Logger LOGGER = Logger.getLogger(XmlController.class.getName());

	@RequestMapping(value = "/xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody ValuesLists getXml(@SessionAttribute("sessionValuesLists") ValuesLists valuesListsReceived) {
		return valuesListsReceived;
	}

	@RequestMapping(value = "/downloadxml", method = RequestMethod.GET)
	public void downloadXML(HttpServletResponse response,
			@SessionAttribute("sessionValuesLists") ValuesLists valuesListsReceived) {
		String prettyValuesLists = null;
		XmlMapper xmlMapper = new XmlMapper();

		response.setContentType("application/xml");
		response.setHeader("Content-Disposition", "attachment; filename=valueslists.xml");

		if (valuesListsReceived != null) {

			try {
				prettyValuesLists = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(valuesListsReceived);
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
