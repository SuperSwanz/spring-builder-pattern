package com.greyseal.patterns;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = ResponseBuilder.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBuilder<T> {
	private final int code;
	private final String message;
	private final boolean hasError;
	private final T data;

	private ResponseBuilder(Builder<T> builder) {
		this.code = builder.code;
		this.message = builder.message;
		this.hasError = builder.hasError;
		this.data = builder.data;
	}

	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Builder<T> {
		private int code;
		private String message;
		private boolean hasError;
		private T data;

		public Builder() {
		}

		public Builder<T> setCode(int code) {
			this.code = code;
			return this;
		}

		public Builder<T> setMessage(String message) {
			this.message = message;
			return this;
		}

		public Builder<T> setHasError(boolean hasError) {
			this.hasError = hasError;
			return this;
		}

		public Builder<T> setData(T data) {
			this.data = data;
			return this;
		}

		public ResponseBuilder<T> build() {
			return new ResponseBuilder<T>(this);
		}
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public boolean isHasError() {
		return hasError;
	}

	public T getData() {
		return data;
	}
	
	//TODO : Params passed can be wrapped into an object for scaling
	public static<T> ResponseBuilder<T> build(T data, int status, String message){
		boolean hasError = false;
		if(data instanceof ErrorBuilder)
			hasError = true;
		return new ResponseBuilder.Builder<T>().setCode(status)
											   .setData(data)
											   .setHasError(hasError)
											   .setMessage(message)
											   .build();
	}
}