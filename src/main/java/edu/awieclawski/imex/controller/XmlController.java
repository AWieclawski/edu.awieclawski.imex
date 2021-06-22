package edu.awieclawski.imex.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import edu.awieclawski.models.ValuesLists;

//landing page for results
@Controller
public class XmlController {

	private final static Logger LOGGER = Logger.getLogger(XmlController.class.getName());

	@GetMapping({ "/xmlstring" })
	public String presentXml(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String prettyValuesLists = null;
		XmlMapper xmlMapper = new XmlMapper();
		ValuesLists valuesListsReceived = (ValuesLists) session.getAttribute("sessionValuesLists");

		if (valuesListsReceived != null)
			try {
				prettyValuesLists = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(valuesListsReceived);
			} catch (JsonProcessingException e) {
				LOGGER.log(Level.SEVERE, "JsonProcessingException fault: " + prettyValuesLists, e);
			}

		model.addAttribute("xmlToDisplay", prettyValuesLists);
		return "/xmlstring";
	}

	@RequestMapping(value = "/xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody ValuesLists getXml(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		ValuesLists valuesListsReceived = (ValuesLists) session.getAttribute("sessionValuesLists");
		return valuesListsReceived;
	}

	@RequestMapping(value = "/downloadxml", method = RequestMethod.GET)
	public void downloadXML(HttpServletResponse response, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		String prettyValuesLists = null;
		XmlMapper xmlMapper = new XmlMapper();
		ValuesLists valuesListsReceived = (ValuesLists) session.getAttribute("sessionValuesLists");

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
