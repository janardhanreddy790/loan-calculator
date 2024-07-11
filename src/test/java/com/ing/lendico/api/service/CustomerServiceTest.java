package com.ing.lendico.api.service;

import com.ing.lendico.api.dto.CustomerDTO;
import com.ing.lendico.api.dto.LoanDataDTO;
import com.ing.lendico.api.dto.RequestLoanAmountResponseDTO;
import com.ing.lendico.api.entity.Customer;
import com.ing.lendico.api.entity.LoanData;
import com.ing.lendico.api.exception.CustomerNotFoundException;
import com.ing.lendico.api.exception.IncorrectAmountException;
import com.ing.lendico.api.repository.CustomerServiceRepository;
import com.ing.lendico.api.repository.LoanDataServiceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerServiceRepository customerServiceRepository;

    @Mock
    private LoanDataServiceRepository loanDataServiceRepository;

    @InjectMocks
    private CustomerService customerService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerService(customerServiceRepository, loanDataServiceRepository);
    }

    @Test
    public void givenCustomerObject_save_newcustomer_loanrequest() throws IncorrectAmountException {
        // given - precondition or setup
        CustomerDTO customerDTO = getCustomerDTO(500.00);


        LoanData loanData = getLoanData();

        when(customerServiceRepository.findByCustomerId(anyLong()))
                .thenReturn((Optional.empty()));
        when(loanDataServiceRepository.save(any()))
                .thenReturn(loanData);

        // when
        UUID uuid = customerService.addCustomer(customerDTO);

        // then - verify the output
        assertThat(uuid).isNotNull();
    }


    @Test
    public void givenCustomerObject_process_existing_customer_loan_request() throws IncorrectAmountException {
        // given
        CustomerDTO customerDTO = getCustomerDTO(500.00);


        Customer customer = getCustomer();

        LoanData loanData = getLoanData();

        when(customerServiceRepository.findByCustomerId(anyLong()))
                .thenReturn((Optional.of(customer)));
        when(loanDataServiceRepository.save(any()))
                .thenReturn(loanData);

        // when
        UUID uuid = customerService.addCustomer(customerDTO);

        // then - verify the output
        assertThat(uuid).isNotNull();
    }


    @Test(expected = IncorrectAmountException.class)
    public void givenCustomerObject_has_invalid_amount() throws IncorrectAmountException {
        // given - precondition or setup
        CustomerDTO customerDTO = getCustomerDTO(100.00);

        Customer customer = getCustomer();

        when(customerServiceRepository.findByCustomerId(anyLong()))
                .thenReturn((Optional.of(customer)));

        // when
        customerService.addCustomer(customerDTO);

    }

    @Test
    public void getCustomerLoanAmount() throws CustomerNotFoundException {
        // given - precondition or setup
        Customer customer = getCustomer();

        RequestLoanAmountResponseDTO requestLoanAmountResponseDTO = new RequestLoanAmountResponseDTO();
        requestLoanAmountResponseDTO.setCustomerId(1234L);
        requestLoanAmountResponseDTO.setTotalRequestedLoanAmount(500.00);

        when(customerServiceRepository.findByCustomerId(anyLong()))
                .thenReturn((Optional.of(customer)));
        when(loanDataServiceRepository.save(any()))
                .thenReturn(requestLoanAmountResponseDTO);

        // when
        RequestLoanAmountResponseDTO response = customerService.getCustomerLoanAmount(1234L);

        // then - verify the output
        assertThat(response).isNotNull();
    }


    @Test(expected = MockitoException.class)
    public void getCustomerLoanAmount_not_found() throws CustomerNotFoundException {
        // given
        RequestLoanAmountResponseDTO requestLoanAmountResponseDTO = new RequestLoanAmountResponseDTO();
        requestLoanAmountResponseDTO.setCustomerId(1234L);
        requestLoanAmountResponseDTO.setTotalRequestedLoanAmount(500.00);

        when(customerServiceRepository.findByCustomerId(anyLong()))
                .thenThrow(new CustomerNotFoundException("Customer Not Found with requested id"));

        // when
        customerService.getCustomerLoanAmount(1234L);
    }

    private LoanData getLoanData() {
        LoanData loanData = new LoanData();
        loanData.setLoanId(UUID.randomUUID());
        loanData.setCustomerId(1234L);
        loanData.setLoanAmount(500.00);
        loanData.setId(1L);
        return loanData;
    }

    private CustomerDTO getCustomerDTO(double v) {
        LoanDataDTO loanDataDTO = new LoanDataDTO();
        loanDataDTO.setLoanAmount(v);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(1234L);
        customerDTO.setCustomerName("name");
        customerDTO.setLoanData(loanDataDTO);
        return customerDTO;
    }

    private Customer getCustomer() {
        Customer customer = new Customer();
        customer.setCustomerName("name");
        customer.setCustomerId(1234L);
        customer.setId(1L);
        return customer;
    }


}
