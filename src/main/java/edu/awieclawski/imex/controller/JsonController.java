package edu.awieclawski.imex.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.awieclawski.models.ValuesLists;

//landing page for results
@Controller
public class JsonController {

	private final static Logger LOGGER = Logger.getLogger(JsonController.class.getName());

	@GetMapping({ "/jsonstring" })
	public String presentJson(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String prettyValuesLists = null;
		ObjectMapper objectMapper = new ObjectMapper();
		ValuesLists valuesListsReceived = (ValuesLists) session.getAttribute("sessionValuesLists");

		if (valuesListsReceived != null)
//			System.out.println("----" + valuesListsReceived.toString());
			// pretty print
			try {
				prettyValuesLists = objectMapper.writerWithDefaultPrettyPrinter()
						.writeValueAsString(valuesListsReceived);
			} catch (JsonProcessingException e) {
				LOGGER.log(Level.SEVERE, "JsonProcessingException fault: " + prettyValuesLists, e);
			}

		model.addAttribute("jsonToDisplay", prettyValuesLists);
		return "/json";
	}

	@RequestMapping(value = "/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ValuesLists getJson(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		ValuesLists valuesListsReceived = (ValuesLists) session.getAttribute("sessionValuesLists");
		return valuesListsReceived;
	}

}
