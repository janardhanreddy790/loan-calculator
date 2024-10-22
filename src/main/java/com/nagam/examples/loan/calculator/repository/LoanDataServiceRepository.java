package com.nagam.examples.loan.calculator.repository;

import com.nagam.examples.loan.calculator.entity.LoanData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanDataServiceRepository extends JpaRepository<LoanData, Long> {
    List<LoanData> findBycustomerId(Long customerId);
}
