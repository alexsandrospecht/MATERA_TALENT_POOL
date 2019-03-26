package com.github.specht.pool.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewEmployeeRequest {

    @NotNull
    @JsonProperty("FirstName")
    private String firstName;

    @NotNull
    @JsonProperty("MiddleInitial")
    private String middleInitial;

    @NotNull
    @JsonProperty("LastName")
    private String lastName;

    @Past
    @NotNull
    @JsonProperty("DateOfBirth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotNull
    @JsonProperty("DateOfEmployment")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfEmployment;

}
