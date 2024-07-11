package com.ing.lendico.api.repository;

import com.ing.lendico.api.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceRepositoryTest {

    @Autowired
    CustomerServiceRepository customerServiceRepository;

    @Test
    public void should_find_no_customer_if_repository_is_empty() {
        // when
        Optional<Customer> customer = customerServiceRepository.findByCustomerId(123456l);

        // then
        assertThat(customer).isEmpty();
    }

    @Test
    public void should_store_a_customer() {
        //Given
        Customer customer = new Customer();
        customer.setCustomerId(123456l);
        customer.setCustomerName("customerName");

        //when
        Long id = customerServiceRepository.save(customer).getId();

        // then
        assertThat(id).isNotNull();
    }
}
