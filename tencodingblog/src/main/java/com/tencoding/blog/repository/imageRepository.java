package com.tencoding.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tencoding.blog.dto.Image;

public interface imageRepository extends JpaRepository<Image, Integer>{

	//SPage<Image> findByStoryTextContaining(String q, Pageable pageable);

	@Query(value = "SELECT * FROM Image WHERE storyText LIKE ?1%", nativeQuery = true)
	Page<Image> findByStoryText(String q, Pageable pageable);
}
