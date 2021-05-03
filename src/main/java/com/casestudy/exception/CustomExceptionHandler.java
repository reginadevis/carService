package com.casestudy.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({ "unchecked", "rawtypes" })
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	@Autowired
	MessageSource messageSource;

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ex.printStackTrace();

		String errorMessage = messageSource.getMessage("common.error", null, request.getLocale());

		return new ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String errorMessage = messageSource.getMessage("invalid.model", null, request.getLocale());

		return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResponseStatusException.class)
	public final ResponseEntity<Object> handleResponseStatusExceptions(Exception ex, WebRequest request){
		ex.printStackTrace();

		String errorMessage = messageSource.getMessage("vin.present", null, request.getLocale());

		return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
	}
}