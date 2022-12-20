package com.tencoding.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.tencoding.blog.dto.KakaoAccount;
import com.tencoding.blog.dto.KakaoProfile;
import com.tencoding.blog.dto.OAuthToken;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.service.UserService;

@Controller
//@RequestMapping("/user")
public class UserController {

	// yml 초기 파라미터를 가져올 수 있다.
	@Value("${tenco.key}")
	private String tencoKey;
	
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

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
	//@ResponseBody // data를 리턴함
	public String kakaoCallback(@RequestParam String code) {
		// 여기서 카카오 서버에서 보내 준 code 값을 받을 수 있다.
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

		HttpEntity<MultiValueMap<String, String>> requestKakaoToken = new HttpEntity<>(params, headers);

		// 헤더 변조 해서 실행 시키는 메서드는 RestTemplate exchange() 이다
		ResponseEntity<OAuthToken> response 
		= rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				requestKakaoToken, OAuthToken.class);

		OAuthToken authToken = response.getBody();
		System.out.println("authToken" + authToken);
		////////////////////////////////////////////////////////////////////// 여기까지 토큰
		////////////////////////////////////////////////////////////////////// 받기 완료

		// 다시 한번 더 kapi.kakao.com 로 토큰을 가지고 요청하여 사용자 정보를 응답 받아야 한다.
		RestTemplate rt2 = new RestTemplate();

		// 헤더 만들기2
		HttpHeaders headers2 = new HttpHeaders();
		// JWT - 주의 : Bearer 다음에 무조건 한 칸 띄우기
		headers2.add("Authorization", "Bearer " + authToken.accessToken);
		headers2.add("Content-Type", "application/x-www-form-urlencoded;");

		// 바디
		HttpEntity<MultiValueMap<String, String>> kakaoDataRequest = new HttpEntity<>(headers2);

		// 파싱 받을 DTO 만들어야 함
		ResponseEntity<KakaoProfile> kakaoDataResponse = rt2.exchange("https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST, kakaoDataRequest, KakaoProfile.class);

		System.out.println("kakaoDataResponse : " + kakaoDataResponse);

		// 우리 blog 서버에 로그인 처리해서 세션 처리... 진행을 해야한다.

		// User object 만들기
		// 1. 회원가입이 되어있어야 한다. --> 자동으로 우리 DB에 넣어야함
		// 2. 소셜 로그인 사용자가 우리 사이트에 이미 회원가입된 사용자라면 DB 저장 X

		KakaoAccount account = kakaoDataResponse.getBody().kakaoAccount;

		// 우리 사이트 회원가입시 필요한 사항 = username, email, password
		// insert2.eat_""
		User kakaoUser = User.builder().username(account.profile.nickname + "_" + kakaoDataResponse.getBody().id)
				.email(account.email).password(tencoKey).oauth("kakao").build();

		System.out.println(" kakao ===> " + kakaoUser);

		// 여기서는 user의 id를 당연히 사용 불가
		// 그러면 username으로 검색하는 기능을 만들어 주어야 한다.
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
	
	
	// 시큐리티 전에 컨트롤러에서 먼저 낚아 채서 여기로 옴!
	// 기존의 로그아웃 처리를 하지 않고, 우리가 재정의한 로그아웃 처리로 넘어옴
	@GetMapping("/m-logout")
	public String logout(HttpServletRequest req, HttpServletResponse res) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(req, res, authentication);
		}
		return "redirect:/";
	}
	

}
