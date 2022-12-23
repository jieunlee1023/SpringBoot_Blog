package com.demo.qlrm.dto.res;

import java.math.BigInteger;
import java.sql.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SalaryDto {

	private int emp_no;
	private int salary;
	private Date from_date;
	private Date to_date;
	private BigInteger salaryCount;

	public SalaryDto(Object[] objs) {
		for (int i = 0; i < objs.length; i++) {
			System.out.println("데이터 타입 확인 : " 
										+ objs[i].getClass().getTypeName());

			this.emp_no = ((Integer) objs[0]);
			this.salary = ((Integer) objs[1]);
			this.from_date = ((Date) objs[2]);
			this.to_date = ((Date) objs[3]);
			this.salaryCount = ((BigInteger) objs[4]);
		}

	}

	public SalaryDto(Integer emp_no, Integer salary, Date from_date, Date to_date, BigInteger salaryCount) {
		this.emp_no = emp_no;
		this.salary = salary;
		this.from_date = from_date;
		this.to_date = to_date;
		this.salaryCount = salaryCount;
	}

}