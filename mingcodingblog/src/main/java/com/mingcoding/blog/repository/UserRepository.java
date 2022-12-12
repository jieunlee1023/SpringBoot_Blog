package com.mingcoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mingcoding.blog.dto.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsernameAndPassword(String username, String password);
	
	
	@Query(value = " SELECT * "
			+ " FROM user "
			+ " WHERE username = ?1 "
			+ " and password = ?2 ",
			nativeQuery = true)
	User login(String username, String password);

}
