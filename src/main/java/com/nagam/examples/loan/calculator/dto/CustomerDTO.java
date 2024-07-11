package com.nagam.examples.loan.calculator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long customerId;

    @NotNull
    private String customerName;

    @JsonProperty("loanData")
    private LoanDataDTO loanData;

}
