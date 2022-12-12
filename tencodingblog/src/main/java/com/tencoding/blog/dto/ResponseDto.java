package com.tencoding.blog.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//같은 변수 이름으로 데이터 타입을 다르게  사용해야할때,
//제네릭 프로그래밍을 생각하자 !
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {
	
	HttpStatus status; //200, 404 ..
	T body;

	
}
