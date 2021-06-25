package edu.awieclawski.imex.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.support.RequestContextUtils;

import edu.awieclawski.models.ValuesLists;

@Controller // landing page for results
public class ResultController {

	@GetMapping({ "/results" })
	public String presentsResults(Model model, HttpServletRequest request,
			@SessionAttribute("sessionValuesLists") ValuesLists valuesListsReceived) {
		// get all FlashAttributes
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null) {
			model.addAttribute("messageToDisplay", inputFlashMap.get("message"));
			model.addAttribute("fileReceivedName", inputFlashMap.get("fileName"));
			model.addAttribute("fileReceivedType", inputFlashMap.get("fileType"));
			model.addAttribute("fileReceivedSize", inputFlashMap.get("fileSize"));
			return "/resultsview";
		} else {
			if (valuesListsReceived == null)
				return "redirect:/up";
			else
				model.addAttribute("messageToDisplay", "Try try to watch or download converted files");
			return "/resultsview";
		}
	}

}
