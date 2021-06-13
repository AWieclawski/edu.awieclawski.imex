package edu.awieclawski.imex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller // landing page for results
public class ResultController {

	@GetMapping({ "/result" })
	public String presentResults(Model model, @ModelAttribute("message") String messageReceived,
			@ModelAttribute("fileName") String fileNameReceived, @ModelAttribute("fileType") String fileTypeReceived,
			@ModelAttribute("fileSize") Long fileSizeReceived) {
		model.addAttribute("messageToDisplay", messageReceived);
		model.addAttribute("fileReceivedName", fileNameReceived);
		model.addAttribute("fileReceivedType", fileTypeReceived);
		model.addAttribute("fileReceivedSize", fileSizeReceived);
		return "/result";
	}

}
