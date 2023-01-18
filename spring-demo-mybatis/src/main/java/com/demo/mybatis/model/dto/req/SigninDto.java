package com.demo.mybatis.model.dto.req;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SigninDto {
	
	private String username;
	private String password;

	// 아이디 저장 여부
	
}
