package edu.awieclawski.imex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

// landing page for results
@Controller
public class ResultController {
	
	@GetMapping({ "/result" })
	public String presentResults(Model model, @ModelAttribute("message") String messageReceived) {
//		System.out.println("--"+messageReceived);
		model.addAttribute("messageToDisplay", messageReceived);
		return "/result";
	}

}
