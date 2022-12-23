package com.demo.qlrm.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import com.demo.qlrm.dto.res.EmployeeDTO;
import com.demo.qlrm.dto.res.SalaryDto;


// JAP Repository <T, ID> 상속을 받으면 
//무조건 Entity(model) 타입으로 받아야 하기 때문에
// DTO로 맵핑하기가 힘들다.
// 그래서 직접 Repository를 구성하자!

@Repository
public class EmployeeRepository {

	// 준비물
	// 1.EntityManager
	private final EntityManager entityManager;

	public EmployeeRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// 기능 설계
	public EmployeeDTO getOldEmployeeAgeInfo() {

		// 어노테이션 기반이 아닌 직접 생성해 보자 native query
		String strQurey = " SELECT " 
		                        + " *, TIMESTAMPDIFF(YEAR, birth_date, NOW()) AS age " 
				                + " FROM "
				                + " employees " 
				                + " WHERE " 
				                + " emp_no = '10001'; ";
// 1. 1단계 하드코딩
//		// Qurey 객체를 사용한다.
//		Query nativeQurery = entityManager.createNativeQuery(strQurey);
//		// Query 객체를 이용해서 구문을 실행하고 결과 집합을 받아보자!
//		nativeQurery.getSingleResult();
//		// 결과 집합이 다중행이면 getResultList() , 단일행이면 getSingleResult
//
//		System.out.println(nativeQurery.getSingleResult());
//		List<Object[]> resultList = nativeQurery.getResultList();
//		System.out.println(resultList.size()); // 사이즈 1개 확인
//		Object[] objs = resultList.get(0);
//
//		// DTO로 변환하기
//		EmployeeDTO dto = new EmployeeDTO(objs);

// 2. qlrm 사용해보기		
		Query nativeQurery = entityManager.createNativeQuery(strQurey);
		JpaResultMapper mapper = new JpaResultMapper();
		EmployeeDTO dto = mapper.uniqueResult(nativeQurery, EmployeeDTO.class);

		// 1 개 이면 단순 Object 를 만들어 주면 된다.
		// 컬럼이 1 개 이상이면 object[] 만들어 줄 수 있다.

		return dto;
	}

	
	
}

//		mapper.list(nativeQurery, <List>,,EmployeeDTO.class)