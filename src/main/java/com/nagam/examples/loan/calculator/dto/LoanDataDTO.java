package com.nagam.examples.loan.calculator.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LoanDataDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private double loanAmount;
}
