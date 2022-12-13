package com.tencoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tencoding.blog.dto.User;

//DAO
// 여기서는 굳이 Bean으로 등록 요청을 하지 않더라도 , 등록을 시켜준다 --> (JpaRepository가 !)
// @Repository
public interface UserRepository extends JpaRepository<User, Integer> {
											 //<테이블명, PK의 데이터타입>
	
	// 없는 함수는 직접 함수를 만들거나
	// Spring JPA 네이밍 전략을 활용한다.
	// SELECT * FROM user WHERE username = ? and password = ?
	
	// 첫번째 방법 -> 네이밍 전략
//	User findByUsernameAndPassword(String username, String password);
	
	// 두번째 방법 -> 네이티브 쿼리 만들기
//	@Query(value = " SELECT * "
//			+ " FROM user "
//			+ " WHERE username = ?1 "
//			+ " and password = ?2 ",
//			nativeQuery = true)
//	User login(String username, String password);
	
}
 