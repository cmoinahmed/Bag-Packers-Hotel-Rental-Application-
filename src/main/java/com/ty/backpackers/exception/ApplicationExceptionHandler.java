package com.ty.backpackers.exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ty.backpackers.dto.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> info = new LinkedHashMap();
		List<FieldError> errors = ex.getFieldErrors();

		for (FieldError error : errors) {
			String fieldname = error.getField();
			String message = error.getDefaultMessage();
			info.put(fieldname, message);
		}

		ResponseStructure<Map<String, String>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage("BAD DATA");
		responseStructure.setData(info);
		return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoIdFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleNoIdFound(NoIdFoundException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(" Not Found");
		responseStructure.setData(exception.getMessage());

		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<ResponseStructure<String>> handleInvalidUser(InvalidUserException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Invalid User");
		responseStructure.setData(exception.getMessage());

		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ResponseStructure<String>> handleInvalidCrendentials(InvalidCredentialsException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.UNAUTHORIZED.value());
		responseStructure.setMessage("Invalid Crendentials");
		responseStructure.setData(exception.getMessage());

		return new ResponseEntity<>(responseStructure, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(CityNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleCityNotFound(CityNotFoundException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Not Found");
		responseStructure.setData(exception.getMessage());

		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}

}
