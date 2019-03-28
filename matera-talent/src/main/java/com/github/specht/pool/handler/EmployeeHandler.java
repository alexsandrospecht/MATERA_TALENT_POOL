package com.github.specht.pool.handler;

import com.github.specht.pool.model.Employee;
import com.github.specht.pool.model.request.NewEmployeeRequest;
import com.github.specht.pool.model.request.UpdateEmployeeRequest;
import com.github.specht.pool.model.response.EmployeeResponse;
import com.github.specht.pool.model.response.PageableResponse;
import com.github.specht.pool.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * This class transforms the request into the entity model, and transforms the return of the service (model) into the response
 *
 * */
@Component
public class EmployeeHandler {

    @Autowired
    private EmployeeService service;

    public EmployeeResponse handleGetById(final String id) {
        final Employee employee = service.findById(id);

        return buildResponse(employee);
    }

    public PageableResponse<EmployeeResponse> handleGetAll(final Integer pageNumber, final Integer pageSize) {

        final Page<Employee> page = service.findAll(pageNumber, pageSize);

        final List<EmployeeResponse> employees = page.stream().map(this::buildResponse).collect(Collectors.toList());

        return PageableResponse.<EmployeeResponse>builder().
                items(employees)
                .actualPage(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    public void handleDelete(final String id) {
        service.delete(id);
    }

    public EmployeeResponse handleNewEmployee(final NewEmployeeRequest request) {
        final Employee employee = buildEmployee(request);
        final Employee output = service.newEmployee(employee);

        return buildResponse(output);
    }

    public EmployeeResponse handleUpdate(final String id, final UpdateEmployeeRequest request) {
        final Employee employee = buildEmployee(request);
        employee.setId(id);
        employee.setStatus(request.getStatus());

        final Employee output = service.updateEmployee(employee);
        return buildResponse(output);
    }

    private EmployeeResponse buildResponse(final Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .dateOfBirth(employee.getDateOfBirth())
                .dateOfEmployment(employee.getDateOfEmployment())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .middleInitial(employee.getMiddleInitial())
                .build();
    }

    private Employee buildEmployee(final NewEmployeeRequest request) {
        return Employee.builder()
                .dateOfBirth(request.getDateOfBirth())
                .dateOfEmployment(request.getDateOfEmployment())
                .firstName(request.getFirstName())
                .middleInitial(request.getMiddleInitial())
                .lastName(request.getLastName())
                .build();
    }
}
