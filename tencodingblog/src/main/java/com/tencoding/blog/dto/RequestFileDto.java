package com.tencoding.blog.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RequestFileDto {
	
//	private MultipartFile[] files; :여러개
	private MultipartFile file;
	private String uuid; //고유한 파일 이름을 만들기 위한 변수
	private String storyText;

	// 기능 만들기
	public Image toEntitiy(String postImageUrl, User user) {
		return Image.builder()
				.storyText(storyText)
				.postImageUrl(postImageUrl)
				.originFileName(file.getOriginalFilename())
				.user(user)
				.build();
	}

}