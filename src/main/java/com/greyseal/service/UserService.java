package com.greyseal.service;

import java.util.Optional;

import com.greyseal.model.User;

public interface UserService {
	Long create(User user);

	Optional<User> get(Long id);

	Optional<User> findByEmail(String email);
}
