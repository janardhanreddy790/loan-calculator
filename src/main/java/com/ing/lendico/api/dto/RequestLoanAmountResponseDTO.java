package com.ing.lendico.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLoanAmountResponseDTO {

    @JsonProperty
    private Long customerId;

    @JsonProperty
    private double totalRequestedLoanAmount;

}
