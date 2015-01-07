package com.invsol.getfoodyc.exceptions;

public class ApplicationException extends Exception {
	/**
	 * Version UID
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public ApplicationException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}