package com.greyseal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greyseal.model.User;
import com.greyseal.repository.UserRepository;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Long create(User user) {
		String email = user.getEmail();
		Optional<User> _user = findByEmail(email);
		if(_user.isPresent()){
			throw new RuntimeException("User Already Exists");
		}
		userRepository.save(user);
		return user.getId();
	}
	
	@Override
	public Optional<User> get(Long id) {
		return Optional.ofNullable(userRepository.findOne(id));
	}
	
	@Override
	public Optional<User> findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return Optional.ofNullable(user);
	}
}