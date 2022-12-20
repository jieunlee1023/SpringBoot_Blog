package com.jecoding.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.jecoding.blog.dto.KakaoAccount;
import com.jecoding.blog.dto.KakaoProfile;
import com.jecoding.blog.dto.OAuthToken;
import com.jecoding.blog.dto.User;
import com.jecoding.blog.service.UserService;


@Controller
public class UserController {
	
	@Value("${tenco.key}")
	private String tencoKey;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/auth/join-form")
	public String joinForm() {
		return "user/join-form";
	}

	@GetMapping("/auth/login-form")
	public String loginForm() {
		return "user/login-form";
	}

	@GetMapping("/user/update-form")
	public String updateForm() {
		return "user/update-form";
	}

	@GetMapping("/m-logout")
	public String logout(HttpServletRequest req, HttpServletResponse res) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(req, res, authentication);
		}
		return "redirect:/";
	}

	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(@RequestParam String code) {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "af497c2db8733a72a5c02d510aa5362a");
		params.add("redirect_uri", "http://localhost:7777/auth/kakao/callback");
		params.add("code", code);

		HttpEntity<MultiValueMap<String, String>> requestKakaoToken = new HttpEntity<>(params, headers);

		// 헤더 변조 해서 실행 시키는 메서드는 RestTemplate exchange() 이다
		ResponseEntity<OAuthToken> response 
		= rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				requestKakaoToken, OAuthToken.class);

		OAuthToken authToken = response.getBody();
		
		//////////////////////////////////////////////////////////////////////

		RestTemplate rt2 = new RestTemplate();

		// 헤더 만들기2
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + authToken.accessToken);
		headers2.add("Content-Type", "application/x-www-form-urlencoded;");

		HttpEntity<MultiValueMap<String, String>> kakaoDataRequest = new HttpEntity<>(headers2);

		ResponseEntity<KakaoProfile> kakaoDataResponse = rt2.exchange("https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST, kakaoDataRequest, KakaoProfile.class);

		KakaoAccount account = kakaoDataResponse.getBody().kakaoAccount;

		User kakaoUser = User.builder().username(account.profile.nickname + "_" + kakaoDataResponse.getBody().id)
				.email(account.email).password(tencoKey).oauth("kakao").build();

		System.out.println(" kakao ===> " + kakaoUser);

		User originUser = userService.searchUserName(kakaoUser.getUsername());
		
		if (originUser.getUsername() == null) {
			System.out.println("신규 회원이기 때문에, 회원가입을 진행");
			userService.saveUser(kakaoUser);
		} else {

		}

		// 신규로 회원가입이든, 기존 한번 가입했던 유저이든 무조건 소셜 로그인이면, 세션을 생성해주어야 한다.
		// 자동 로그인 처리 -> 시큐리티 세션에다가 강제 저장
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), tencoKey));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/";
	}

}
