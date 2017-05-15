package com.greyseal.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.greyseal.constant.Constant;
import com.greyseal.exception.GreyException;
import com.greyseal.model.User;
import com.greyseal.patterns.ResponseBuilder;
import com.greyseal.patterns.UserBuilder;
import com.greyseal.service.UserService;

@Controller
@RequestMapping(value = "/user", produces="application/json", consumes="application/json")
public class UserController extends BaseController<Long, Serializable> {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<ResponseBuilder<UserBuilder>> create(@RequestBody UserBuilder userDTO, UriComponentsBuilder b) {
		User user = UserBuilder.toUser(userDTO);
		userService.create(user);
		Long userId = user.getId();
		UriComponents uriComponents = b.path("/user/get/{id}").buildAndExpand(userId);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponents.toUri());
		ResponseBuilder<UserBuilder> response = ResponseBuilder.build(UserBuilder.toDTO(user), HttpStatus.CREATED.value(), Constant.MESSAGE_SUCCESS);
		return new ResponseEntity<ResponseBuilder<UserBuilder>>(response, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/get/{email:.+}", method = RequestMethod.GET)
	public ResponseEntity<ResponseBuilder<UserBuilder>> get(@PathVariable("email") String email) {
		User user = userService.findByEmail(email)
							   .orElseThrow(() -> new GreyException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "User not found: " + email));
		ResponseBuilder<UserBuilder> response = ResponseBuilder.build(UserBuilder.toDTO(user), HttpStatus.OK.value(), Constant.MESSAGE_SUCCESS);
		return new ResponseEntity<ResponseBuilder<UserBuilder>>(response, HttpStatus.OK);
	}
}