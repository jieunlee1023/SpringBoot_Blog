package com.tencoding.blog.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Image;
import com.tencoding.blog.dto.RequestFileDto;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.repository.imageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoryService {

	private final imageRepository imageRepository;

	@Value("${file.path}")
	private String uploadFolder;

	public void imageUpload(RequestFileDto fileDto, PrincipalDetail principalDetail) {
		// UUID 사용법
		UUID uuid = UUID.randomUUID();
		System.out.println("uuid: " + uuid);

		// 서버 컴퓨터에 파일 저장시 이름을 만들어야 한다
		String imageFileName = uuid + "_" + fileDto.getFile().getOriginalFilename();
		System.out.println("imageFileName : " + imageFileName);

		// 실제로 이미지를 저장 할 경로를 가지고 와야한다.
		// 하드 코딩 할 수 있지만, 초기 파라미터 방식으로 만들어보자.
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		System.out.println("파일 패스:" + imageFilePath);

		// 파일 만들기 하는 방법
		try {
			Files.write(imageFilePath, fileDto.getFile().getBytes());
			// DB 저장 로직 추가
			Image image = fileDto.toEntitiy(imageFileName, principalDetail.getUser());
			imageRepository.save(image);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Transactional
	public Page<Image> searchBoard(String q, Pageable pageable){
		return imageRepository.findByStoryText(q,pageable);
	}


	
}
