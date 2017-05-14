package com.greyseal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greyseal.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "select u from User u where u.email = ?1")
	public User findByEmail(String email);
}
