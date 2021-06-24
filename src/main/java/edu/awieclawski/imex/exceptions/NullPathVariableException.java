package edu.awieclawski.imex.exceptions;

public class NullPathVariableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1537521947289529753L;
	private final static String messageTxt = "Path Variable can not be null!";

	@Override
	public String getMessage() {
		return messageTxt;
	}
	
}
