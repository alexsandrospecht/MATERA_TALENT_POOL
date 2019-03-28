package com.github.specht.pool.controller;


import com.github.specht.pool.handler.EmployeeHandler;
import com.github.specht.pool.model.request.NewEmployeeRequest;
import com.github.specht.pool.model.request.UpdateEmployeeRequest;
import com.github.specht.pool.model.response.EmployeeResponse;
import com.github.specht.pool.model.response.ErrorResponse;
import com.github.specht.pool.model.response.PageableResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/api/v1/employees")
@Api(description = "CRUD Operations for Employee")
public class EmployeeController {

    @Autowired
    private EmployeeHandler employeeHandler;

    @ApiOperation(value = "Retrieves one employee by it's id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = EmployeeResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class)
    })
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EmployeeResponse> findEmployeeById(@PathVariable("id") final String id) {

        final EmployeeResponse response = employeeHandler.handleGetById(id);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Retrieves all employees")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EmployeeResponse.class, responseContainer = "List")
    })
    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PageableResponse<EmployeeResponse>> findAllEmployees(
            @RequestParam(value = "pageNumber", required = false) final Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) final Integer pageSize) {

        final PageableResponse<EmployeeResponse> response = employeeHandler.handleGetAll(pageNumber, pageSize);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Create a new employee")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = EmployeeResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class)
    })
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EmployeeResponse> newEmployee(@Valid @RequestBody final NewEmployeeRequest request) {

        final EmployeeResponse response = employeeHandler.handleNewEmployee(request);
        return ResponseEntity.created(URI.create("/api/v1/employees/" + response.getId()))
                .body(response);
    }

    @ApiOperation(value = "Removes a employee")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") final String id) {

        employeeHandler.handleDelete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = EmployeeResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class)
    })
    @ApiOperation(value = "Update a employee")
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable("id") final String id,
            @Valid @RequestBody UpdateEmployeeRequest request) {

        final EmployeeResponse response = employeeHandler.handleUpdate(id, request);
        return ResponseEntity.ok(response);
    }

}
