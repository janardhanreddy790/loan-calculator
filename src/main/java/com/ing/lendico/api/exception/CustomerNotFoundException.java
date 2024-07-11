package com.ing.lendico.api.exception;

/**
 * When a requested customer is not found by the JPA then this exception will be thrown
 * @see com.ing.lendico.api.repository.LoanDataServiceRepository
 */
public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}