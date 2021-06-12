package edu.awieclawski.imex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.awieclawski.models.ValuesLists;

//landing page for results
@Controller
public class JsonController {

	@GetMapping({ "/json" })
	public String presentJson(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String prettyValuesLists = null;
		ObjectMapper objectMapper = new ObjectMapper();
		ValuesLists valuesListsReceived = (ValuesLists) session.getAttribute("sessionValuesLists");
		
		if (valuesListsReceived != null)
			System.out.println("----" + valuesListsReceived.toString());
		// pretty print
		try {
			prettyValuesLists = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(valuesListsReceived);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("jsonToDisplay", prettyValuesLists);
		return "/json";
	}

}
