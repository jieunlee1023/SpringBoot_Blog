package com.tencoding.blog.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.tencoding.blog.model.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//1. mysql server 실행
//2. 테이블 생성
//3. 제약 추가

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@DynamicInsert // null 필드가 들어올때 무시하라 --> 디폴트 값 선언하면 적용 됨!ㄴ
public class User {

	@Id //Primary key PK로 
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB 넘버링 전략을 따라간다.
	private int id;
	
	@Column(nullable = false, length = 30)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault(" 'user' ") //문자라는 것을 알려주어야 한다.
	//DynamicInsert를 안쓰고 직접 입력하겠금 만듦!
	@Enumerated(EnumType.STRING) //DB에게 String 타입이라고 알려줘야 한다.
	private RoleType role; //admin, user, manager : Enum 타입으로 변경
	
	@CreationTimestamp //시간이 자동으로 입력된다.
	private Timestamp createDate;
	
}


/*
 * 
 * {

	"username" : "홍길동",
	"password" : "asd1234",
	"email" : "a@naver.com",

}
 * 
 * 
 * 
 * 
 */

