package com.tencoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tencoding.blog.dto.User;

//DAO
// 여기서는 굳이 Bean으로 등록 요청을 하지 않더라도 , 등록을 시켜준다 --> (JpaRepository가 !)
// @Repository
public interface UserRepository extends JpaRepository<User, Integer> {
											 //<테이블명, PK의 데이터타입>

	
	
}
