package com.nagam.examples.loan.calculator.exception;

import com.nagam.examples.loan.calculator.service.CustomerService;

/**
 * When customer requests a loan amount more than specified amount then this exception will be thrown
 * @see CustomerService addCustomer() method.
 */
public class IncorrectAmountException extends Exception {

    public IncorrectAmountException(String errorMessage) {
        super(errorMessage);
    }
}