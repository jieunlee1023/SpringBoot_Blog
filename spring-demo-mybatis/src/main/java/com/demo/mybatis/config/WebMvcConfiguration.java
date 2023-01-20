package com.demo.mybatis.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demo.mybatis.intercepter.LoginIntercepter;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	// 필터 등록
	@Bean
	public FilterRegistrationBean<?> filterBean() {
		FilterRegistrationBean<AddFilter> registrationBean = new FilterRegistrationBean<>(new AddFilter());

		registrationBean.setOrder(Integer.MIN_VALUE); // 필터 여러개 적용 시 순번
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	}

	// 인터셉터 등록
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		.addInterceptor(new LoginIntercepter()) 
		.addPathPatterns("/auth/**", "/api/**");
//		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
