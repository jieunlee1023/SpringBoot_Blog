package com.demo.mybatis.intercepter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.demo.mybatis.model.dto.common.User;
import com.demo.mybatis.model.dto.res.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginIntercepter implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("-------------------------------------");
		System.out.println(request.getRequestURI());
		System.out.println("-------------------------------------");

		String uri = request.getRequestURI();
		HttpSession session = request.getSession();
		User principal = (User) session.getAttribute("principal");
		System.out.println("principal :" + principal);

		if (principal == null) {
			if (uri.contains("api")) {
				// 다르게 응답
				response.setContentType("application/json; charset=utf-8");
				PrintWriter out = response.getWriter();
				ResponseDto<String> responseDto = new ResponseDto<>(-1, "잘못된  접근입니다." , "권한이 없습니다.");
				ObjectMapper mapper = new  ObjectMapper();
				String json = mapper.writeValueAsString(responseDto);
				out.println(json);
				out.flush(); //반드시 사용하
			} else {
				response.sendRedirect("/user/signin-form");
			}
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
