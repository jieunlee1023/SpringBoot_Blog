package com.jieuncoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jieuncoding.blog.dto.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
