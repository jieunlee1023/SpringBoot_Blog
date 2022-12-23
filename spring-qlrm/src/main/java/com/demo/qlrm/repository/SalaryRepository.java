package com.demo.qlrm.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import com.demo.qlrm.dto.res.SalaryDto;

@Repository
public class SalaryRepository {
	
	private final EntityManager entityManager;
	
	public SalaryRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public ArrayList<SalaryDto> getSalaryCount(){
		// 1
		ArrayList<SalaryDto> list = new ArrayList<>();
		String strQuery = " SELECT *, count(emp_no) as salaryCount " 
				                + " FROM salaries " + " GROUP BY emp_no "
						        + " HAVING count(emp_no) < 10 " 
				                + " ORDER BY emp_no desc " 
						        + " LIMIT 10 ; ";
		Query nativeQuery = entityManager.createNativeQuery(strQuery);
				
		List<Object[]> resultList = nativeQuery.getResultList();
		
		for(int i=0; i< resultList.size(); i++) {
			Object[] object = resultList.get(i);
			SalaryDto dto = new SalaryDto(object);
			list.add(dto);
		}
		
		return list;
	}
	
	public List<SalaryDto> getSalaryCountQlrm(){
		
		String strQuery = " SELECT *, count(emp_no) as salaryCount " 
				                + " FROM salaries " + " GROUP BY emp_no "
				                + " HAVING count(emp_no) < 10 " 
				                + " ORDER BY emp_no desc " 
				                + " LIMIT 10 ; ";
		
		Query nativeQuery = entityManager.createNativeQuery(strQuery);
		JpaResultMapper mapper = new JpaResultMapper();
		List<SalaryDto> dto = mapper.list(nativeQuery, SalaryDto.class);

		return dto;
	}
	
	

}
