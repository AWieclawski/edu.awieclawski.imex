package edu.awieclawski.imex.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // global controller
public class ExceptionsController {

	@ExceptionHandler(Exception.class)
	public String handleExceptions(Exception e) {
		return "/";
	}

}
