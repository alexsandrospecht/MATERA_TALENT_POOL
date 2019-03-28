package com.github.specht.pool.service;

import com.github.specht.pool.model.Employee;
import org.springframework.data.domain.Page;

public interface EmployeeService {

	Employee findById(String id);

	Page<Employee> findAll(Integer pageNumber, Integer pageSize);

	void delete(String id);

	Employee newEmployee(Employee employee);

	Employee updateEmployee(Employee employee);

}
