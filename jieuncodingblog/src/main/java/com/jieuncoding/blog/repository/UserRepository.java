package com.jieuncoding.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jieuncoding.blog.dto.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
	Optional<User> findByUsername(String username);
}
