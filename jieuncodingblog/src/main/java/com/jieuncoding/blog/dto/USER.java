package com.jieuncoding.blog.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.jieuncoding.blog.model.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class USER {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 30, unique = true)
	private String username;

	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length = 50)
	private String email;

	@Enumerated(EnumType.STRING)
	private RoleType role;

	@CreationTimestamp
	private Timestamp createDate;

}
