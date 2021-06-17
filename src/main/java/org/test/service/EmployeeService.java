package org.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.test.dto.EmployeeDTO;
import org.test.entity.Employee;
import org.test.entity.Person;
import org.test.entity.Position;
import org.test.repository.EmployeeRepository;
import org.test.repository.PositionRepository;

@Component
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PositionRepository positionRepository;

	public List<EmployeeDTO> findEmployees(String position, String name) {
		List<Employee> employees = new ArrayList<>();
		if (position == null && name == null) {
			employeeRepository.findAll().forEach(employees::add);
		} else if (position != null && name != null) {
			employees = employeeRepository.findByPersonNameAndPositionName(name, position);
		} else if (position != null && name == null) {
			employees = employeeRepository.findByPositionName(position);
		} else if (position == null && name != null) {
			employees = employeeRepository.findByPersonName(name);
		}
		return employees.stream().map(e -> new EmployeeDTO(e)).collect(Collectors.toList());
	}

	public EmployeeDTO save(EmployeeDTO employee, String id) {
		Position position = positionRepository.findByName(employee.getPosition());
		if (position == null) {
			return null;
		}
		Employee employeeEntity = new Employee();
		employeeEntity.setPerson(new Person());
		if (id != null) {
			employeeEntity = employeeRepository.findById(id).orElse(null);
			if (employeeEntity == null) {
				return null;
			}
		}
		employeeEntity.setPosition(position);
		employeeEntity.getPerson().setAddress(employee.getAddress());
		employeeEntity.getPerson().setCellphone(employee.getCellphone());
		employeeEntity.getPerson().setCityName(employee.getCityName());
		employeeEntity.getPerson().setLastName(employee.getLastName());
		employeeEntity.getPerson().setName(employee.getName());
		employeeEntity.setSalary(employee.getSalary() == null ? 0.0 : employee.getSalary());
		employeeEntity = employeeRepository.save(employeeEntity);
		return new EmployeeDTO(employeeEntity);
	}

	public void delete(String id) {
		employeeRepository.deleteById(id);
	}
}
