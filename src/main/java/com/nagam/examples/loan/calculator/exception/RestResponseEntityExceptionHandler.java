package com.nagam.examples.loan.calculator.exception;

import com.nagam.examples.loan.calculator.service.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global Exception handler
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * When a requested customer is not found by the JPA then this exception will be thrown
     *
     * @see CustomerService addCustomer() method.
     */
    @ExceptionHandler(IncorrectAmountException.class)
    public ResponseEntity<Object> handleIncorrectAmountException(IncorrectAmountException incorrectAmountException, WebRequest request) {
        String bodyOfResponse = "Loan Amount should be between 500.00 and 12000.50";
        return handleExceptionInternal(incorrectAmountException, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * When a requested customer is not found by the JPA then this exception will be thrown
     *
     * @see CustomerService getCustomerLoanAmount() method.
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomertNotFoundException(CustomerNotFoundException customerNotFoundException, WebRequest request) {
        String bodyOfResponse = "Please check the customerId, Requested customer not found.";
        return handleExceptionInternal(customerNotFoundException, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
