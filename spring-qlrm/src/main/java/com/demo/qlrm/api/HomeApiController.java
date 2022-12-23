package com.demo.qlrm.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.qlrm.dto.res.EmployeeDTO;
import com.demo.qlrm.repository.EmployeeRepository;

@RestController
@RequestMapping("/home")
public class HomeApiController {

	// 여기에서는 Service를 만들지 않고 바로 repository test 하기!
	private final EmployeeRepository employeeRepository;

	public HomeApiController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@GetMapping({ "", "/index", "/home" })
	public ResponseEntity<?> home() {
		// ObjectMapper 클래스가 돌아감!
		EmployeeDTO resultDto = employeeRepository.getOldEmployeeAgeInfo();
		return new ResponseEntity<EmployeeDTO>(resultDto, HttpStatus.OK);
	}

//	@GetMapping({"", "/index", "/home"})
//	public ResponseEntity<?> home() {
//		SelaryDTO result = employeeRepository.getOldEmployeeAgeInfo();
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}

}
