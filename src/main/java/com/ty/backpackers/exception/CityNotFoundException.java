package com.ty.backpackers.exception;

public class CityNotFoundException extends RuntimeException{

	private String message = "City Not Found";

	 public CityNotFoundException(){

	}

	public CityNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
