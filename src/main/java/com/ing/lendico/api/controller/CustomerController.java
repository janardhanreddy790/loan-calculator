package com.ing.lendico.api.controller;

import com.ing.lendico.api.dto.CustomerDTO;
import com.ing.lendico.api.dto.RequestLoanAmountResponseDTO;
import com.ing.lendico.api.exception.CustomerNotFoundException;
import com.ing.lendico.api.exception.IncorrectAmountException;
import com.ing.lendico.api.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping(path = "/customer")
public class CustomerController {


    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO customerDTO) throws IncorrectAmountException {
        UUID loanAccountId = customerService.addCustomer(customerDTO);
        String message = "Customer Requested amount of : " + customerDTO.getLoanData().getLoanAmount() + "has been created with loanAccountId: " + loanAccountId;
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @GetMapping("/getLoan")
    public ResponseEntity<RequestLoanAmountResponseDTO> getCustomerLoanAmount(@RequestParam Long customerId) throws CustomerNotFoundException {
        RequestLoanAmountResponseDTO requestLoanAmountResponseDTO = customerService.getCustomerLoanAmount(customerId);
        return new ResponseEntity<>(requestLoanAmountResponseDTO, HttpStatus.OK);
    }
}
