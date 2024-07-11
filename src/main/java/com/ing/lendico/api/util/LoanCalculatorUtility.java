package com.ing.lendico.api.util;

import com.ing.lendico.api.dto.CustomerDTO;
import com.ing.lendico.api.entity.Customer;
import com.ing.lendico.api.entity.LoanData;

public class LoanCalculatorUtility {

    /**
     * Maps CustomerDTO object with the Customer entity to persist
     * @param customerDTO request
     * @return customerEntity
     */
     public static Customer mapDTOToCustomer(CustomerDTO customerDTO) {
        Customer customerEntity = new Customer();
        customerEntity.setCustomerId(customerDTO.getCustomerId());
        customerEntity.setCustomerName(customerDTO.getCustomerName());
        return customerEntity;
    }

    /**
     * Maps LoanDataDTO object with the LoanData entity to persist
     * @param customerDTO request
     * @return loanData
     */
     public static LoanData mapDTOToLoanData(CustomerDTO customerDTO) {
        LoanData loanData = new LoanData();
        loanData.setLoanAmount(customerDTO.getLoanData().getLoanAmount());
        loanData.setCustomerId(customerDTO.getCustomerId());
        return loanData;
    }
}
