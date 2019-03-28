package com.github.specht.pool.model;

import com.github.specht.pool.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employee")
public class Employee {

    @Id
    private String id;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private LocalDate dateOfBirth;
    private LocalDate dateOfEmployment;
    private Status status;

}
