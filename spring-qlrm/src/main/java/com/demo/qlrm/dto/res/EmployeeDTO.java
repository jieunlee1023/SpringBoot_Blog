package com.demo.qlrm.dto.res;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Iterator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class EmployeeDTO {

	private int emp_no;
	private Date birth_date;
	private String first_name;
	private String last_name;
	private Character gender;
	private Date hire_date;
	private BigInteger age;

	public EmployeeDTO(Object[] objs) {

		for (int i = 0; i < objs.length; i++) {
			System.out.println(objs[i]);
			// 런타임시 데이터 타입 확인
			System.out.println("데이터 타입 확인 : " + objs[i].getClass().getTypeName());
		}

		this.emp_no = ((Integer) objs[0]);
		this.birth_date = ((Date) objs[1]);
		this.first_name = ((String) objs[2]);
		this.last_name = ((String) objs[3]);
		this.gender = ((Character) objs[4]);
		this.hire_date = ((Date) objs[5]);
		this.age = ((BigInteger) objs[6]);

	}

	// 전체 생성자를 활용하면 알아서 Jpamapper 녀석 호출해서 맵핑해준다.
	public EmployeeDTO(Integer emp_no, Date birth_date, String first_name, String last_name, Character gender,
			Date hire_date, BigInteger age) {
		this.emp_no = emp_no;
		this.birth_date = birth_date;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.hire_date = hire_date;
		this.age = age;

	}

	public EmployeeDTO(Integer emp_no, Date birth_date, String first_name, String last_name, Character gender,
			Date hire_date) {

		this(emp_no, birth_date, first_name, last_name, gender, hire_date, null);
	}

}
