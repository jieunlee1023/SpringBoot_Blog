package com.tencoding.blog.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Image;
import com.tencoding.blog.dto.Kakaopay;
import com.tencoding.blog.dto.RequestFileDto;
import com.tencoding.blog.service.StoryService;

@Controller
@RequestMapping("/story")
public class StoryController {

	@Autowired
	private StoryService storyService;

	// http://localhost:9090/story/home
	@GetMapping("/home")
	public String storyHome(Model model,
			@PageableDefault(size = 4, sort = "id", direction = Direction.DESC) Pageable pageable) {

		Page<Image> imagePage = storyService.getImageList(pageable);
		model.addAttribute("imagePage", imagePage);

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

	
	//http://localhost:9090/story/kakaopay
	@GetMapping("/kakaopay")
	@ResponseBody
	public Kakaopay kakaoPay() {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.add("Authorization", "KakaoAK 88fa30940fa732bbc640e78e7a446b6d");
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		MultiValueMap<String, String> params = new  LinkedMultiValueMap<>();
		params.add("cid", "TC0ONETIME");
		params.add("partner_order_id", "9090");
		params.add("partner_user_id", "jieun");
		params.add("item_name", "스티커");
		params.add("quantity", "1");
		params.add("total_amount", "1");
		params.add("tax_free_amount", "0");
		params.add("approval_url", "http://localhost:9090/");
		params.add("cancel_url", "http://localhost:9090/");
		params.add("fail_url", "http://localhost:9090/");
		
		HttpEntity<MultiValueMap<String, String>> requestKakaopay
		= new HttpEntity<>(params,headers);
		
		ResponseEntity<Kakaopay> response
		= rt.exchange(
				"https://kapi.kakao.com/v1/payment/ready", 
				HttpMethod.POST, 
				requestKakaopay, 
				Kakaopay.class);
		
		return response.getBody();
	}
}
