package com.demo.mybatis.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("ADD 필터 초기화");
	}
	/**
	 * 전/후 처리 Request, Response 가 필터를 거칠 때 수행되는
	 * 메서드가 chain.doFilter 기점으로 나누어 진다.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String requestURI = req.getRequestURI();
		log.info("--Request("+requestURI+") 필터 --");
		chain.doFilter(request, response);
		log.info("--Response("+res.getContentType()+") 필터 --");
		
	}
	
	@Override
	public void destroy() {
		System.out.println("Add 필터 소멸");
	}

}
