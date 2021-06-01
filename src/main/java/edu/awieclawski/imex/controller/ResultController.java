package edu.awieclawski.imex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// landing page for results
@Controller
public class ResultController {
	@GetMapping({ "/result"})
	public String hello(Model model) {
		return "/result";
	}

}
