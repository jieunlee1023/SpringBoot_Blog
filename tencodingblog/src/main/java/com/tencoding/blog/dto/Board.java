package com.tencoding.blog.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 150)
	private String title;

	@Lob // 대용량 데이터 선언
	private String content;

	@ColumnDefault("0")
	private int count; // 조회수

	// 연관관계 설정
	@ManyToOne(fetch = FetchType.EAGER)
	// EAGER : 한번에 틀과 데이터를 동시에 가져옴
	// Lazy : 틀만 가져오고 데이터는 안가지고 옴
	@JoinColumn(name = "userId") // 컬럼명을 직접 지정
//	@JsonManagedReference
	private User user;

	// 테이블을 생성하는 것이 아니라, 오브젝트를 다룰 때 가지고 오도록 요청 (mappedBy)
	// Board <---> Reply 관계
	// 연관관계의 주인이 아니다. (select 할 때 가지고 와야하는 데이터이다)
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // board는 변수명과 관계없이 오브젝트 이름을 가져와야함
	@OrderBy("id desc") // 정렬 주는 방법
//	@JsonBackReference
	@JsonIgnoreProperties({"board"}) // Reply 안에 있는 board getter 를 무시하면 호출이 안된다.
	private List<Reply> replys;
	// reply - FK board table 생성이 된다. 1 정규화 위반!

	@CreationTimestamp
	private Timestamp createDate;

}
