package com.github.specht.pool.objects;

import com.github.specht.pool.model.request.NewEmployeeRequest;

import java.time.LocalDate;

public class NewEmployeeRequestTestObject {

	public static final String FIRST_NAME = "Test";
	public static final String MIDDLE_INITIAL = "T";
	public static final String LAST_NAME = "Testing";
	public static final LocalDate BIRTH_DATE = LocalDate.of(2000, 1, 1);
	public static final LocalDate EMPLOYMENT_DATE = LocalDate.of(2019, 2, 5);


	public static NewEmployeeRequest build() {
		return NewEmployeeRequest.builder()
				.firstName(FIRST_NAME)
				.middleInitial(MIDDLE_INITIAL)
				.lastName(LAST_NAME)
				.dateOfBirth(BIRTH_DATE)
				.dateOfEmployment(EMPLOYMENT_DATE)
				.build();
	}

}
