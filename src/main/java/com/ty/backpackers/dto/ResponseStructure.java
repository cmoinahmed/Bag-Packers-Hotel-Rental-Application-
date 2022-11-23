package com.ty.backpackers.dto;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	
	private int status;
	
	private String message;
	
	private T data;

}
