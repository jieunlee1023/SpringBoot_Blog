package com.tencoding.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Image;
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

}
