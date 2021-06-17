package org.test.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Position extends BaseEntity {

	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
	private List<Employee> employees;

	protected Position() {
		super();
	}

	public Position(String name) {
		this();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}