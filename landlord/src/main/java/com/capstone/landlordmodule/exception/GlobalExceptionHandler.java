package com.capstone.landlordmodule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(LandlordNotFoundException.class)
	public ResponseEntity<String> handleLandlordNotFoundException(Exception e){
		ResponseEntity<String> responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		return responseEntity;
		
	}
}