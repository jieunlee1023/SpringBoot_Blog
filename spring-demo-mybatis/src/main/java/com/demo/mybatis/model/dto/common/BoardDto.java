package com.demo.mybatis.model.dto.common;



import java.sql.Timestamp;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BoardDto {

	private  int id;
	private String title;
	private String content;
	private Timestamp createDate;
	
	private String username;

}
