package com.tencoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.tencoding.blog.dto.OAuthToken;

@Controller
//@RequestMapping("/user")
public class UserController {

	@Autowired
	private HttpSession session;

	@GetMapping("/auth/login_form")
	public String loginForm() {
		// 경로 : /WEB-INF/views/user/login_form.jsp;
		return "user/login_form";
	}

	@GetMapping("/auth/join_form")
	public String joinForm() {
		return "user/join_form";
	}

	@GetMapping("/user/update_form")
	public String updateForm() {
		return "user/update_form";
	}

//	POST /oauth/token HTTP/1.1
//	Host: kauth.kakao.com
//	Content-type: application/x-www-form-urlencoded;charset=utf-8

	// https://kauth.kakao.com/oauth/token

	// 페이지에서 데이터를 리턴하는 방법
	@GetMapping("/auth/kakao/callback")
	@ResponseBody // data를 리턴함
	public OAuthToken kakaoCallback(@RequestParam String code) {
		// 여기서  카카오 서버에서 보내 준 code 값을 받을 수 있다.
		// 다음 단계는 토큰 발급 받기
		
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// 바디 만들기
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "a3af89fb8269257123c6854ffafc500d");
		params.add("redirect_uri", "http://localhost:9090/auth/kakao/callback");
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> requestKakaoToken 
		= new HttpEntity<>(params, headers);
		
		//헤더 변조 해서 실행 시키는 메서드는 RestTemplate exchange() 이다
		ResponseEntity<OAuthToken> response = rt.exchange("https://kauth.kakao.com/oauth/token", 
				HttpMethod.POST,
				requestKakaoToken,
				OAuthToken.class);
		
		
		return response.getBody();
	}

// 기존 스프링에서 로그아웃 처리는 따로 정리 !
//	@GetMapping("/logout")
//    public String logout() {
//
//        HttpSession httpSession = session;
//        httpSession.invalidate(); // 로그아웃 처리
//
//        return "redirect:/";
//    }

}
