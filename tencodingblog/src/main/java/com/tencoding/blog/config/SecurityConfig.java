package com.tencoding.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration 
//IoC 관리
@EnableWebSecurity 
//시큐리티 필터로 등록이 된다. (필터 커스텀)
@EnableGlobalMethodSecurity(prePostEnabled = true) 
//특정 주소로 접근하면 권한 및 인증 처리를 미리 체크하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// /auth/ 라고 따라오는 녀석들은 check 하지 않는다.
	// ex) /auth/login_form , /auth/join_form
	// --> /auth/** (auth 밑에 오는 녀석들 모두 선택(허용)하겠다)
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable(); // 개발 완료 전 테스트시 사용한다.(실 서비스에서는 사용안함 권장)
		
		
		http
		.authorizeHttpRequests()
		 .antMatchers("/auth/**", "/","/js/**", "/image/**", "/css/**" )
		 .permitAll()
		 .anyRequest()
		 .authenticated()
		.and()
		 .formLogin()
		 .loginPage("/auth/login_form");
	
	}
	
	
	
}
