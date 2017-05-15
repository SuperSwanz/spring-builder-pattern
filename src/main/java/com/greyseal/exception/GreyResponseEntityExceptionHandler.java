package com.greyseal.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
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
		return new ResponseEntity<ResponseBuilder<ErrorBuilder>>(buildResponse(ex), new HttpHeaders(), ex.getStatus());
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		GreyException gx = new GreyException(status.value(), status, status.getReasonPhrase());
		return new ResponseEntity<Object>(buildResponse(gx), new HttpHeaders(), status );
	}
	
	protected ResponseBuilder<ErrorBuilder> buildResponse (final GreyException ex){
		ErrorBuilder error = new ErrorBuilder.Builder()
				 							.setStatus(ex.getCode())
			 								.setMessage(ex.getMessage())
			 								.setTimeStamp(new Date().toString())
			 								.build();
		return ResponseBuilder.build(error, ex.getCode(), ex.getMessage());
	}
}
