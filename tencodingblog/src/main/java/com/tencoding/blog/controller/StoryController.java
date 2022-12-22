package com.tencoding.blog.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Image;
import com.tencoding.blog.dto.Kakaopay;
import com.tencoding.blog.dto.PaymentKakao;
import com.tencoding.blog.dto.RequestFileDto;
import com.tencoding.blog.service.StoryService;
import com.tencoding.blog.service.UserService;

@Controller
@RequestMapping("/story")
public class StoryController {

	String tid;

	@Autowired
	private StoryService storyService;

	@Autowired
	private UserService userService;

	// http://localhost:9090/story/home
	@GetMapping({"/home", "/search"})
	public String storyHome(Model model,
			@PageableDefault(size = 4, sort = "id", direction = Direction.DESC) Pageable pageable,
			@RequestParam(required = false) String q) {
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+q);
		String searchTitle = q == null ? "" : q;

		Page<Image> images = storyService.searchBoard(searchTitle, pageable);
		model.addAttribute("imagePage", images);
		
		int PAGENATION_BLOCK_COUNT = 3;
		
		int nowPage = images.getPageable().getPageNumber() + 1;
		int startPageNumber = Math.max(nowPage - PAGENATION_BLOCK_COUNT, 1);
		int endPageNumber = Math.min(nowPage+PAGENATION_BLOCK_COUNT, images.getTotalPages());

		
		ArrayList<Integer> pageNumbers = new ArrayList<>();
		for (int i = startPageNumber; i <= endPageNumber; i++) {
			pageNumbers.add(i);
		}
		
		model.addAttribute("images",images);
		model.addAttribute("nowPage",nowPage);
		model.addAttribute("startPage",startPageNumber);
		model.addAttribute("endPage",endPageNumber);
		model.addAttribute("pageNumbers",pageNumbers);
		model.addAttribute("q", searchTitle);
		
		return "story/home";

	}

	// http://localhost:9090/story/upload
	@GetMapping("/upload")
	public String storyUpload() {
		return "story/upload";
	}

	// 여기서는 데이터를 전달받고 처리해야한다.
	@PostMapping("/image/upload")
//	@ResponseBody
	// MultipartFile 다루는 1단계
//	public String storyImageUpload(MultipartFile file, String storyText) {
	public String storyImageUpload(RequestFileDto fileDto, @AuthenticationPrincipal PrincipalDetail principalDetail) {

		storyService.imageUpload(fileDto, principalDetail);
		return "redirect:/story/home";
	}

	// http://localhost:9090/story/kakaopay
	@GetMapping("/kakaopay")
	@ResponseBody
	public Kakaopay kakaoPay() {

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.add("Authorization", "KakaoAK 88fa30940fa732bbc640e78e7a446b6d");
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", "TC0ONETIME");
		params.add("partner_order_id", "9090");
		params.add("partner_user_id", "1023");
		params.add("item_name", "스티커");
		params.add("quantity", "1");
		params.add("total_amount", "1");
		params.add("tax_free_amount", "0");
		params.add("approval_url", "http://localhost:9090/story/kakaopay/success");
		params.add("cancel_url", "http://localhost:9090/story/home");
		params.add("fail_url", "http://localhost:9090/");

		HttpEntity<MultiValueMap<String, String>> requestKakaopay = new HttpEntity<>(params, headers);

		ResponseEntity<Kakaopay> response = rt.exchange("https://kapi.kakao.com/v1/payment/ready", HttpMethod.POST,
				requestKakaopay, Kakaopay.class);
		tid = response.getBody().tid;
		return response.getBody();

	}

	@GetMapping("/kakaopay/success")
	public String kakaopaySuccess(@RequestParam(name = "pg_token") String pg_token,
			@AuthenticationPrincipal PrincipalDetail principalDetail) {

		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<pg_token : " + pg_token);
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<kakaopay.tid : " + tid);

		userService.searchUserName(principalDetail.getUsername());

		RestTemplate rt2 = new RestTemplate();
		HttpHeaders headers2 = new HttpHeaders();

		headers2.add("Authorization", "KakaoAK 88fa30940fa732bbc640e78e7a446b6d");
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params2 = new LinkedMultiValueMap<>();
		params2.add("cid", "TC0ONETIME");
		params2.add("tid", tid);
		params2.add("partner_order_id", "9090");
		params2.add("partner_user_id", "1023");
		params2.add("pg_token", pg_token);

		HttpEntity<MultiValueMap<String, String>> requestKakaopay2 = new HttpEntity<>(params2, headers2);

		System.out.println("111111111111111111111");
		ResponseEntity<PaymentKakao> response2 = rt2.exchange("https://kapi.kakao.com/v1/payment/approve",
				HttpMethod.POST, requestKakaopay2, PaymentKakao.class);

		System.out.println("22222222222222222222222");
		System.out.println(response2.getBody());

		return "redirect:/";
	}

}
