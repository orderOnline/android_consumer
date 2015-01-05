package com.invsol.getfoodyc.exceptions;

public class ServerException extends Exception {

	private static final long serialVersionUID = 2L;
	private String message;

	public ServerException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}