package com.github.specht.pool.controller;

import com.github.specht.pool.model.request.NewEmployeeRequest;
import com.github.specht.pool.model.request.UpdateEmployeeRequest;
import com.github.specht.pool.model.response.EmployeeResponse;
import com.github.specht.pool.model.response.ErrorResponse;
import com.github.specht.pool.objects.NewEmployeeRequestTestObject;
import com.github.specht.pool.objects.UpdateEmployeeRequestTestObject;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 *
 * Integration Tests for Creating, Updating and Removing an employee.
 *
 * */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class EmployeeApiIT extends AbstractApiTest {

	@Test
	public void givenValidNewEmployeeRequestWhenCallPostThenShouldReturnCreated() throws Exception {

		final NewEmployeeRequest request = NewEmployeeRequestTestObject.build();
		final MockHttpServletResponse response = mvc.perform(post("/api/v1/employees")
						.content(objectMapper.writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertThat(response.getStatus(), equalTo(HttpStatus.CREATED.value()));

		final EmployeeResponse employee = objectMapper
				.readValue(response.getContentAsString(), EmployeeResponse.class);

		assertThat(employee.getId(), notNullValue());
		assertThat(employee.getFirstName(), equalTo(NewEmployeeRequestTestObject.FIRST_NAME));
		assertThat(employee.getMiddleInitial(), equalTo(NewEmployeeRequestTestObject.MIDDLE_INITIAL));
		assertThat(employee.getLastName(), equalTo(NewEmployeeRequestTestObject.LAST_NAME));
		assertThat(employee.getDateOfBirth(), equalTo(NewEmployeeRequestTestObject.BIRTH_DATE));
		assertThat(employee.getDateOfEmployment(), equalTo(NewEmployeeRequestTestObject.EMPLOYMENT_DATE));
	}

	@Test
	public void givenValidUpdateEmployeeRequestWhenCallPutThenShouldReturnSuccess() throws Exception {

		final UpdateEmployeeRequest request = UpdateEmployeeRequestTestObject.build();
		final MockHttpServletResponse response = mvc.perform(put("/api/v1/employees/c2a94be9-3bd0-49c2-8160-1749e26877f2")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertThat(response.getStatus(), equalTo(HttpStatus.OK.value()));

		final EmployeeResponse employee = objectMapper
				.readValue(response.getContentAsString(), EmployeeResponse.class);

		assertThat(employee.getId(), equalTo("c2a94be9-3bd0-49c2-8160-1749e26877f2"));
	}

	@Test
	public void givenInvalidUpdateEmployeeRequestWhenCallPutThenShouldReturnErrorMessages() throws Exception {

		final MockHttpServletResponse response = mvc.perform(put("/api/v1/employees/c2a94be9-3bd0-49c2-8160-1749e26877f2")
				.content(objectMapper.writeValueAsString(new UpdateEmployeeRequest()))
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertThat(response.getStatus(), equalTo(HttpStatus.BAD_REQUEST.value()));

		final ErrorResponse employee = objectMapper
				.readValue(response.getContentAsString(), ErrorResponse.class);

		final List<String> errors = employee.getErrors();
		assertThat(errors, hasSize(6));
		assertThat(errors, Matchers.hasItem(Matchers.equalTo("lastName: must not be null")));
		assertThat(errors, Matchers.hasItem(Matchers.equalTo("firstName: must not be null")));
		assertThat(errors, Matchers.hasItem(Matchers.equalTo("status: must not be null")));
		assertThat(errors, Matchers.hasItem(Matchers.equalTo("middleInitial: must not be null")));
		assertThat(errors, Matchers.hasItem(Matchers.equalTo("dateOfBirth: must not be null")));
		assertThat(errors, Matchers.hasItem(Matchers.equalTo("firstName: must not be null")));
		assertThat(errors, Matchers.hasItem(Matchers.equalTo("dateOfEmployment: must not be null")));
	}

	@Test
	public void givenEmployeeWhenCallDeleteThenShouldReturnNoContentStatus() throws Exception {

		final MockHttpServletResponse response = mvc.perform(delete("/api/v1/employees/e379ada4-d2cf-4338-b7af-07b977e94486")
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertThat(response.getStatus(), equalTo(HttpStatus.NO_CONTENT.value()));
	}

}
