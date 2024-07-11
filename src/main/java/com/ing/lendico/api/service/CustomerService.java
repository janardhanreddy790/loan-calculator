package com.ing.lendico.api.service;

import com.ing.lendico.api.dto.CustomerDTO;
import com.ing.lendico.api.dto.RequestLoanAmountResponseDTO;
import com.ing.lendico.api.entity.Customer;
import com.ing.lendico.api.entity.LoanData;
import com.ing.lendico.api.exception.CustomerNotFoundException;
import com.ing.lendico.api.exception.IncorrectAmountException;
import com.ing.lendico.api.repository.CustomerServiceRepository;
import com.ing.lendico.api.repository.LoanDataServiceRepository;
import com.ing.lendico.api.util.LoanCalculatorUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CustomerService {

    public static final double DOUBLE_500 = 500.00;
    public static final double DOUBLE_12000_50 = 12000.50;
    private final CustomerServiceRepository customerServiceRepository;
    private final LoanDataServiceRepository loanDataServiceRepository;

    @Autowired
    public CustomerService(CustomerServiceRepository customerServiceRepository, LoanDataServiceRepository loanDataServiceRepository) {
        this.customerServiceRepository = customerServiceRepository;
        this.loanDataServiceRepository = loanDataServiceRepository;
    }

    public UUID addCustomer(CustomerDTO customerDTO) throws IncorrectAmountException {

        log.info("@addCustomer service method start");
        UUID loanAccountId;
        Optional<Customer>  customer =  isCustomerExists(customerDTO);

        log.debug("Service: customer name:{} ",customerDTO.getCustomerName());
        log.debug("Service: customer ID:{} ",customerDTO.getCustomerId());

        // Validation and Business Logic
        if(customer.isPresent() && customer.get().getCustomerId().equals(customerDTO.getCustomerId())){
            loanAccountId = handleIfCustomerExists(customerDTO);
        }else {
            loanAccountId = handleIfCustomerNotExists(customerDTO);
        }

        log.info("@addCustomer service method end");
        return loanAccountId;
    }

    private UUID handleIfCustomerNotExists(CustomerDTO customerDTO) throws IncorrectAmountException {
        UUID loanAccountId;
        if (customerDTO.getLoanData().getLoanAmount() >= DOUBLE_500 && customerDTO.getLoanData().getLoanAmount() <= DOUBLE_12000_50) {
            Customer customerEntity = LoanCalculatorUtility.mapDTOToCustomer(customerDTO);
            customerServiceRepository.save(customerEntity);
            LoanData loanData = LoanCalculatorUtility.mapDTOToLoanData(customerDTO);
            loanAccountId = loanDataServiceRepository.save(loanData).getLoanId();
        }else{
            throw new IncorrectAmountException("Loan Amount should be between 500.00 and 12000.50");
        }
        return loanAccountId;
    }

    private UUID handleIfCustomerExists(CustomerDTO customerDTO) throws IncorrectAmountException {
        UUID loanAccountId;
        if (customerDTO.getLoanData().getLoanAmount() >= DOUBLE_500 && customerDTO.getLoanData().getLoanAmount() <= DOUBLE_12000_50) {
            LoanData loanData = LoanCalculatorUtility.mapDTOToLoanData(customerDTO);
            loanAccountId = loanDataServiceRepository.save(loanData).getLoanId();
        }else{
            throw new IncorrectAmountException("Loan Amount should be between 500.00 and 12000.50");
        }
        return loanAccountId;
    }

    private Optional<Customer> isCustomerExists(CustomerDTO customerDTO){

        return customerServiceRepository.findByCustomerId(customerDTO.getCustomerId());
    }

    public RequestLoanAmountResponseDTO getCustomerLoanAmount(Long customerId) throws CustomerNotFoundException {
        log.info("@getCustomerLoanAmount service method end");
        double totalAmount;
        Optional<Customer> customer =  customerServiceRepository.findByCustomerId(customerId);

        // check if customer exists
        if(customer.isPresent()) {
            List<LoanData> loans = loanDataServiceRepository.findBycustomerId(customer.get().getCustomerId());
            totalAmount = calculateTotalLoanAmount(loans);
            log.debug("calculateTotalLoanAmount: {}", totalAmount);
        }else{
            throw new CustomerNotFoundException("Requested customer not found");
        }
        log.info("@getCustomerLoanAmount service method end");
        return new RequestLoanAmountResponseDTO(customer.get().getCustomerId(),totalAmount);
    }

    private double calculateTotalLoanAmount(List<LoanData> loans){
       return loans.stream().mapToDouble(LoanData::getLoanAmount).sum();
    }
}
