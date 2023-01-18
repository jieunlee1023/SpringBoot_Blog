package com.demo.mybatis.model.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {

	
	private int code; // 1 성공 ,-1 실패
	private String message;
	private T body;
}
