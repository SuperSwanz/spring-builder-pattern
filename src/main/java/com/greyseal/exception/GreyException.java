package com.greyseal.exception;

import org.springframework.http.HttpStatus;

public class GreyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2262812408478748939L;

	private int code;
	private HttpStatus status;

	public GreyException() {
		this(500, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public GreyException(int code, HttpStatus status) {
		this(code, status, "Error while processing the request", null);
	}

	public GreyException(int code, HttpStatus status, String message) {
		this(code, status, message, null);
	}

	public GreyException(int code, HttpStatus status, String message, Throwable throwable) {
		super(message, throwable);
		this.code = code;
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public HttpStatus getStatus() {
		return status;
	}
}