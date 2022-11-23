package com.ty.backpackers.exception;


public class NoIdFoundException extends RuntimeException{

	private String message = "Id not found";

	public NoIdFoundException() {
	}

	public NoIdFoundException(String message) {
		
		this.message =  message;
	}

	public String getMessage() {
		return message;
	}


}


