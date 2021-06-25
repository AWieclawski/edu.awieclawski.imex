package edu.awieclawski.imex.controller;

//import java.util.logging.Level;
//import java.util.logging.Logger;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice // global controller
public class ExceptionsController {

//	private final static Logger LOGGER = Logger.getLogger(ExceptionsController.class.getName());
	public static final String DEFAULT_ERROR_VIEW = "/error";

	@ExceptionHandler(Exception.class) // universal for all child exceptions
	public String handleExceptions(Exception e, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		redirectAttributes.addFlashAttribute("errorName", e.getClass().getSimpleName());
		return "redirect:/error";
	}

}
