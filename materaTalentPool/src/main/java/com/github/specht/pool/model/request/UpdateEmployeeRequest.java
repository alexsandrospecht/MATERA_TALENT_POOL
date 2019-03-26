package com.github.specht.pool.model.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.specht.pool.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
public class UpdateEmployeeRequest extends NewEmployeeRequest {

    @NotNull
    @JsonProperty("ID")
    private String id;

    @NotNull
    @JsonProperty("Status")
    private Status status;

}
