package com.ty.backpackers.exception;

public class InvalidCredentialsException extends RuntimeException{
	
	private String message = "Invalid Crendentials";

	 public InvalidCredentialsException(){

	}

	public InvalidCredentialsException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
