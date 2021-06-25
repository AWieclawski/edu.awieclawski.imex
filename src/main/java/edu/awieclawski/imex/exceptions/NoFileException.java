package edu.awieclawski.imex.exceptions;

public class NoFileException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6737521947232129753L;

	public NoFileException(String message) {
		super(message);
	}

	public NoFileException(String message, Throwable cause) {
		super(message, cause);
	}
}