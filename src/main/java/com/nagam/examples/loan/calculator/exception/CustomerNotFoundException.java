package com.nagam.examples.loan.calculator.exception;

import com.nagam.examples.loan.calculator.repository.LoanDataServiceRepository;

/**
 * When a requested customer is not found by the JPA then this exception will be thrown
 * @see LoanDataServiceRepository
 */
public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}