package com.greyseal.patterns;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.greyseal.model.User;

@JsonDeserialize(builder = UserBuilder.Builder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBuilder {
	// These are Required parameters
	private final String email;

	// These are Optional parameters
	private final String firstName;
	private final String lastName;
	
	private UserBuilder(Builder builder) {
		this.email = builder.email;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
	}

	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Builder {
		// Setting Required parameters		
		private final String email;
		
		// Setting Optional parameters
		private String firstName;
		private String lastName;
		
		public Builder(@JsonProperty("email") String email) {
			this.email = email;
			this.firstName = null;
			this.lastName = null;
		}

		public Builder setFirstName(@JsonProperty String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder setLastName(@JsonProperty String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public UserBuilder build() {
			return new UserBuilder(this);
		}
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public static User toUser(UserBuilder userDTO){
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		return user;	
	}

	public static UserBuilder toDTO(User user) {
		UserBuilder userDTO = new UserBuilder.Builder(user.getEmail())
											 .setFirstName(user.getFirstName())
											 .setLastName(user.getLastName())
											 .build();
		
		return userDTO;
	}
}