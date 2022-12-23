package com.demo.qlrm.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.qlrm.dto.res.SalaryDto;
import com.demo.qlrm.repository.SalaryRepository;

@RestController
public class SalaryApiController {

	private final SalaryRepository salaryRepository;

	public SalaryApiController(SalaryRepository salaryRepository) {
		this.salaryRepository = salaryRepository;
	}

	@GetMapping("/salarycount")
	public ResponseEntity<?> salaryCount() {
		ArrayList<SalaryDto> result = salaryRepository.getSalaryCount();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/salarycount-qlrm")
	public ResponseEntity<?> salaryCountQlrm() {
		List<SalaryDto> resultQlrm = salaryRepository.getSalaryCountQlrm();
		return new ResponseEntity<>(resultQlrm, HttpStatus.OK);
	}

}
