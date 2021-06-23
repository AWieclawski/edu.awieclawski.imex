package edu.awieclawski.imex.controller;

//import java.util.logging.Level;
//import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

//	private final static Logger LOGGER = Logger.getLogger(HelloController.class.getName());

	@GetMapping({ "/hello", "/welcome", "/home", "/" })
	public String hello(Model model, HttpSession session) {

		// resets all session attributes
		session.invalidate();
		return "/welcome";
	}

}
