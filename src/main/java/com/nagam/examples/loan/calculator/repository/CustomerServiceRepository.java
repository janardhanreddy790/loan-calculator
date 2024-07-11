package com.nagam.examples.loan.calculator.repository;

import com.nagam.examples.loan.calculator.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerServiceRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCustomerId(Long customerId);
}
