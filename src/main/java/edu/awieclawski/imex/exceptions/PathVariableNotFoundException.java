package edu.awieclawski.imex.exceptions;

public class PathVariableNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -655879687833769917L;
	private String messageTxt;

	public PathVariableNotFoundException(String pathVariable) {
		super("Path Variable " + pathVariable + " not found!");
		setMessageTxt("Path Variable " + pathVariable + " not found!");
	}

	public PathVariableNotFoundException(String pathVariable, Throwable cause) {
		super("Path Variable " + pathVariable + " not found!");
		setMessageTxt("Path Variable " + pathVariable + " not found!");
	}

	@Override
	public String getMessage() {
		String msg;

		if (this.messageTxt != null && !this.messageTxt.isEmpty()) {
			msg = messageTxt;
		} else
			msg = super.getMessage();
		return msg;
	}

	public String getMessageTxt() {
		return messageTxt;
	}

	public void setMessageTxt(String messageTxt) {
		this.messageTxt = messageTxt;
	}

}
