package org.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.test.dto.EmployeeDTO;
import org.test.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(method = RequestMethod.GET)
	List<EmployeeDTO> get(@RequestParam(required = false) String position,
			@RequestParam(required = false) String name) {
		return employeeService.findEmployees(position, name);
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> create(@RequestBody EmployeeDTO employee) {
		EmployeeDTO newEmployee = employeeService.save(employee, null);
		if (newEmployee != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	ResponseEntity<?> update(@RequestBody EmployeeDTO employee, @PathVariable String id) {
		EmployeeDTO newEmployee = employeeService.save(employee, id);
		if (newEmployee != null) {
			return ResponseEntity.status(HttpStatus.OK).body(newEmployee);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	ResponseEntity<?> delete(@PathVariable String id) {
		try {
			employeeService.delete(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	}

}
