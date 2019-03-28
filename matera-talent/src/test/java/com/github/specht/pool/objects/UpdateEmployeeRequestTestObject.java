package com.github.specht.pool.objects;

import com.github.specht.pool.enums.Status;
import com.github.specht.pool.model.request.UpdateEmployeeRequest;

import java.time.LocalDate;

public class UpdateEmployeeRequestTestObject {

	public static final String ID = "c2a94be9-3bd0-49c2-8160-1749e26877f2";
	public static final String FIRST_NAME = "Johny";
	public static final String MIDDLE_INITIAL = "A.";
	public static final String LAST_NAME = "Smith";
	public static final LocalDate BIRTH_DATE = LocalDate.of(2000, 1, 1);
	public static final LocalDate EMPLOYMENT_DATE = LocalDate.of(2019, 2, 5);

	public static UpdateEmployeeRequest build() {
		final UpdateEmployeeRequest request = new UpdateEmployeeRequest();
		request.setId(ID);
		request.setStatus(Status.ACTIVE);
		request.setDateOfBirth(BIRTH_DATE);
		request.setDateOfEmployment(EMPLOYMENT_DATE);
		request.setFirstName(FIRST_NAME);
		request.setMiddleInitial(MIDDLE_INITIAL);
		request.setLastName(LAST_NAME);

		return request;
	}

}
