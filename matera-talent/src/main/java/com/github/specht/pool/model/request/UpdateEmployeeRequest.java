package com.github.specht.pool.model.request;

import com.github.specht.pool.enums.Status;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateEmployeeRequest extends NewEmployeeRequest {

    @NotNull
    private String id;

    @NotNull
    private Status status;

}
