package org.test.dto;

import org.test.entity.Employee;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO implements Comparable<Object> {
	private String id;
	private String name;
	private String lastName;
	private String address;
	private String cellphone;
	private String cityName;
	private String position;
	private Double salary;

	public EmployeeDTO() {

	}

	public EmployeeDTO(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getPerson().getName();
		this.lastName = employee.getPerson().getLastName();
		this.address = employee.getPerson().getAddress();
		this.cellphone = employee.getPerson().getCellphone();
		this.cityName = employee.getPerson().getCityName();
		this.position = employee.getPosition().getName();
		this.salary = employee.getSalary();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int compareTo(Object o) {
		EmployeeDTO other = (EmployeeDTO) o;
		return other.getSalary().compareTo(this.getSalary());
	}

}
