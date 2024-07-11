package com.ing.lendico.api.repository;

import com.ing.lendico.api.entity.LoanData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanDataServiceRepository extends JpaRepository<LoanData, Long> {
    List<LoanData> findBycustomerId(Long customerId);
}
