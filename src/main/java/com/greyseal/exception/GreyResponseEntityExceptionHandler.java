package com.greyseal.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.greyseal.patterns.ErrorBuilder;
import com.greyseal.patterns.ResponseBuilder;

@ControllerAdvice
public class GreyResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	public GreyResponseEntityExceptionHandler() {
		super();
	}

	@ExceptionHandler(value = { GreyException.class })
	public ResponseEntity<ResponseBuilder<ErrorBuilder>> handleUserNotFoundException(final GreyException ex, final HttpServletRequest request) {
		ErrorBuilder error = new ErrorBuilder.Builder()
											 .setStatus(ex.getCode())
											 .setMessage(ex.getMessage())
											 .setTimeStamp(new Date().toString())
											 .build();
		ResponseBuilder<ErrorBuilder> response = ResponseBuilder.build(error, ex.getCode(), ex.getMessage());
		return new ResponseEntity<ResponseBuilder<ErrorBuilder>>(response, new HttpHeaders(), ex.getStatus());
	}
}
