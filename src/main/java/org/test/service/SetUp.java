package org.test.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.test.entity.Employee;
import org.test.entity.Person;
import org.test.entity.Position;
import org.test.repository.EmployeeRepository;
import org.test.repository.PositionRepository;

@Component
public class SetUp implements ApplicationRunner {
	private EmployeeRepository employeeRepository;
	private PositionRepository positionRepository;

	public SetUp(EmployeeRepository employeeRepository, PositionRepository positionRepository) {
		this.employeeRepository = employeeRepository;
		this.positionRepository = positionRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<Position> positions = new ArrayList<>();
		String[] positionNames = { "Manager", "System Engineer", "President", "Secretary", "QA" };
		Arrays.stream(positionNames).forEach(name -> {
			Position position = new org.test.entity.Position(name);
			positions.add(position);
		});
		positionRepository.saveAll(positions);

		Random rand = new Random();
		List<Employee> employees = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			employees.add(employeeStructure("Test" + i, "Test" + i,
					positionRepository.findByName(positionNames[rand.nextInt(5)]), rand.nextDouble() * 1000));
		}
		employeeRepository.saveAll(employees);
	}

	private Employee employeeStructure(String name, String lastName, Position position, Double salary) {
		Employee employee = new Employee();
		employee.setPerson(new Person(name, lastName));
		employee.setPosition(position);
		employee.setSalary(salary);
		return employee;
	}
}
