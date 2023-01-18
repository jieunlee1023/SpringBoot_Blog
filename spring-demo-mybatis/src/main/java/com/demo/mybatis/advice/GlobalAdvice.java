package com.demo.mybatis.advice;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.mybatis.model.dto.common.SiteError;

@ControllerAdvice
public class GlobalAdvice {

	@ExceptionHandler(value = Exception.class)
	public void exception(Exception e) {
		System.out.println("-----------------------");
		System.out.println(e.getClass().getName());
		System.out.println(e.getMessage());
		System.out.println("-----------------------");
	}

	//1단계
	@ExceptionHandler(value = DuplicateKeyException.class)
	public ResponseEntity<?> duplicateKeyException(DuplicateKeyException e) {
		System.out.println(e.getMessage());
		return new ResponseEntity<>("동일한 아이디가 존재합니다.", HttpStatus.BAD_REQUEST);
	}
	
	//2단계
	@ExceptionHandler(value = UniqeUserNameException.class)
	public String uniqeUserNameException(UniqeUserNameException e , Model model) {
		
		//3단계
		//추가
		SiteError error = new SiteError();
		error.setMessage(e.getDefaultMessage());
		error.setField(e.getField());
		error.setInvalidValue(e.getInvalidValue());
		
		model.addAttribute("isError", true);
		model.addAttribute("error", error);
		return "user/signup_form";
	}

}
