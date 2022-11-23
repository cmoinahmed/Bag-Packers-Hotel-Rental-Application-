package com.ty.backpackers.exception;

public class InvalidUserException extends RuntimeException {

	private String message = "Email Not found";

	public InvalidUserException() {

	}

	public InvalidUserException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
