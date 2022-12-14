package com.tencoding.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tencoding.blog.auth.PrincipalDetailService;

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

	@Autowired
	private PrincipalDetailService principalDetailService;

	// IoC 관리하고 싶어서 여기서 선언
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 1. userDetailsService 들어갈 Object 만들어 주어야 한다.
		// 2. passwordEncoder 우리가 사용하는 해시 암호화 함수를 알려주어야 한다.

		// 1. 우리가 커스텀한 녀석을 넣어야 한다.
		// 2. BCryptPasswordEncoder 사용해서 암호화 하였다.
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable(); // 개발 완료 전 테스트시 사용한다.(실 서비스에서는 사용안함 권장)

		http.authorizeHttpRequests()
		.antMatchers("/auth/**", "/", "/js/**", "/image/**", "/css/**")
		.permitAll() 
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/auth/login_form")
		.loginProcessingUrl("/auth/loginProc")
		.defaultSuccessUrl("/");
//		 .failureUrl("/false/실패시 커스텀 주소)
		;

	}

}
