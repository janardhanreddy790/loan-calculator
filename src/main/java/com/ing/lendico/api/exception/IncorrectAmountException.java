package com.ing.lendico.api.exception;

/**
 * When customer requests a loan amount more than specified amount then this exception will be thrown
 * @see com.ing.lendico.api.service.CustomerService addCustomer() method.
 */
public class IncorrectAmountException extends Exception {

    public IncorrectAmountException(String errorMessage) {
        super(errorMessage);
    }
}