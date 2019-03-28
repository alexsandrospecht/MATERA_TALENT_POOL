package com.github.specht.pool.controller;


import com.github.specht.pool.handler.EmployeeHandler;
import com.github.specht.pool.model.request.NewEmployeeRequest;
import com.github.specht.pool.model.request.UpdateEmployeeRequest;
import com.github.specht.pool.model.response.EmployeeResponse;
import com.github.specht.pool.model.response.PageableResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/api/v1/employee")
@Api(description = "CRUD Operations for Employee")
public class EmployeeController {

    @Autowired
    private EmployeeHandler employeeHandler;

    @ApiOperation(value = "Retrieves one employee by it's id")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EmployeeResponse> findEmployeeById(@PathVariable("id") String id) {

        final EmployeeResponse response = employeeHandler.handleGetById(id);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Retrieves all employees")
    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PageableResponse<EmployeeResponse>> findAllEmployees(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        final PageableResponse<EmployeeResponse> response = employeeHandler.handleGetAll(pageNumber, pageSize);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Create a new employee")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EmployeeResponse> newEmployee(@Valid @RequestBody NewEmployeeRequest request) {

        final EmployeeResponse response = employeeHandler.handleNewEmployee(request);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Removes a employee")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") String id) {

        employeeHandler.handleDelete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Update a employee")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EmployeeResponse> updateEmployee(@Valid @RequestBody UpdateEmployeeRequest request) {

        final EmployeeResponse response = employeeHandler.handleUpdate(request);
        return ResponseEntity.ok(response);
    }

}
