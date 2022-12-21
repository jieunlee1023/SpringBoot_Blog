package com.tencoding.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${file.path}")
	private String uploadFolder;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		// --> /upload/** 낚아채어 간다
		// url 패턴이 : /upload/파일명 --> 가져감
		registry.addResourceHandler("/upload/**")
		.addResourceLocations("file:///" + uploadFolder) // 실제 파일 서버 물리적인 경로
		.setCachePeriod(60 * 10 * 6) //한번 열었다면 캐시를 줌 (캐시 지속시간 설정) - 초단위
		.resourceChain(true) // 자원 찾는 것을 최적화 하기 위해 설정
		.addResolver(new PathResourceResolver());
		
	}
	
}
