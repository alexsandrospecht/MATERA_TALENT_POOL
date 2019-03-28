package com.github.specht.pool.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.specht.pool.handler.GlobalExceptionHandler;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class AbstractApiTest {

	@Autowired
	private EmployeeController employeeController;

	@Autowired
	private GlobalExceptionHandler globalExceptionHandler;

	@Autowired
	protected ObjectMapper objectMapper;

	protected MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(employeeController)
				.setControllerAdvice(globalExceptionHandler)
				.build();
	}

}
