package edu.awieclawski.imex.controller;

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	private final static Logger LOGGER = Logger.getLogger(HelloController.class.getName());

	@GetMapping({ "/hello", "/welcome", "/home", "/" })
	public String hello(Model model, HttpServletRequest request) {

		// reset all session attributes
		HttpSession session = request.getSession();
		Enumeration<String> attributeNames = session.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String tmpAttr = attributeNames.nextElement();
			session.removeAttribute(tmpAttr);
			if (tmpAttr != null)
				LOGGER.log(Level.INFO, "Attribute removed: " + tmpAttr.toString());
		}
		return "/welcome";
	}

}
