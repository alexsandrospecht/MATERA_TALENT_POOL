package com.github.specht.pool.service;

import com.github.specht.pool.enums.Status;
import com.github.specht.pool.exception.NotFoundException;
import com.github.specht.pool.model.Employee;
import com.github.specht.pool.repository.EmployeeRepository;
import com.github.specht.pool.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Supplier;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee findById(final String id) {
        return repository.findByIdAndStatus(id, Status.ACTIVE)
                .orElseThrow(buildNotFoundException(id));
    }

    @Override
    public Page<Employee> findAll(final Integer pageNumber, final Integer pageSize) {
        return repository.findAllByStatus(Status.ACTIVE, PageUtil.buildPage(pageNumber, pageSize));
    }

    @Override
    public void delete(final String id) {
        final Employee employee = repository.findById(id)
                .orElseThrow(buildNotFoundException(id));

        employee.setStatus(Status.INACTIVE);

        repository.save(employee);
    }

    @Override
    public Employee newEmployee(final Employee employee) {
        employee.setId(UUID.randomUUID().toString());
        employee.setStatus(Status.ACTIVE);

        return repository.save(employee);
    }

    @Override
    public Employee updateEmployee(final Employee employee) {
        return repository.save(employee);
    }

    private Supplier<? extends RuntimeException> buildNotFoundException(final String id) {
        return () -> new NotFoundException(String.format("Employee with id %s not found!", id));
    }

}
