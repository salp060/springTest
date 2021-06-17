package org.test.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.test.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

	List<Employee> findByPersonName(String name);

	List<Employee> findByPersonNameAndPositionName(String name, String position);

	List<Employee> findByPositionName(String position);
}
