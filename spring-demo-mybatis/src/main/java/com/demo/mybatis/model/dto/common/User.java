package com.demo.mybatis.model.dto.common;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

	private int id;
	private String username;
	private String password;
	private String email;
	private String profile;
	private String role;
	private Timestamp createDate;
}
