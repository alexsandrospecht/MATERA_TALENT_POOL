package com.github.specht.pool.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.specht.pool.model.response.EmployeeResponse;
import com.github.specht.pool.model.response.ErrorResponse;
import com.github.specht.pool.model.response.PageableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 *
 * Integration Tests for searching employee(s).
 *
 * */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindEmployeeApiIT extends AbstractApiTest {

	@Test
	public void givenValidIdWhenCallGetOnEmployeeThenShouldReturnValue() throws Exception {
		final MockHttpServletResponse response = mvc.perform(
				get("/api/v1/employees/e379ada4-d2cf-4338-b7af-07b977e94486")
						.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertThat(response.getStatus(), equalTo(HttpStatus.OK.value()));

		final EmployeeResponse employee = objectMapper
				.readValue(response.getContentAsString(), EmployeeResponse.class);

		assertThat(employee.getFirstName(), equalTo("John"));
		assertThat(employee.getMiddleInitial(), equalTo("A."));
		assertThat(employee.getLastName(), equalTo("Snow"));
		assertThat(employee.getId(), equalTo("e379ada4-d2cf-4338-b7af-07b977e94486"));
	}

	@Test
	public void givenInvalidIdWhenCallGetOnEmployeeThenShouldNotReturnValue() throws Exception {
		final MockHttpServletResponse response = mvc.perform(
				get("/api/v1/employees/not-valid-id")
						.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertThat(response.getStatus(), equalTo(HttpStatus.NOT_FOUND.value()));

		final ErrorResponse error = objectMapper
				.readValue(response.getContentAsString(), ErrorResponse.class);

		assertThat(error.getErrors(), hasSize(1));
		assertThat(error.getErrors().get(0), equalTo("Employee with id not-valid-id not found!"));
	}

	@Test
	public void givenCallOnGetAllThenShouldReturnAllEmployeesInDatabase() throws Exception {
		final MockHttpServletResponse response = mvc.perform(
				get("/api/v1/employees")
						.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertThat(response.getStatus(), equalTo(HttpStatus.OK.value()));

		final PageableResponse<EmployeeResponse> page = objectMapper
				.readValue(response.getContentAsString(), new TypeReference<PageableResponse<EmployeeResponse>>(){});

		assertThat(page.getItems(), hasSize(2));
		assertThat(page.getActualPage(), equalTo(0));
		assertThat(page.getPageSize(), notNullValue());
		assertThat(page.getTotalElements(), equalTo(2L));
		assertThat(page.getTotalPages(), equalTo(1));
	}

}
