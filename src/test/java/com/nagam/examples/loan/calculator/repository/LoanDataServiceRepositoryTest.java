package com.nagam.examples.loan.calculator.repository;

import com.nagam.examples.loan.calculator.entity.LoanData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LoanDataServiceRepositoryTest {

    @Autowired
    LoanDataServiceRepository loanDataServiceRepository;

    @Test
    public void should_find_no_loandata_if_repository_is_empty() {
        // when
        List<LoanData> loanDataList = loanDataServiceRepository.findBycustomerId(123456l);

        // then
        assertThat(loanDataList).isEmpty();
    }

    @Test
    public void should_find_loanData_if_exists() {
        // Given
        LoanData loanData = new LoanData();
        loanData.setCustomerId(123456l);
        loanData.setLoanAmount(600.00);
        loanData.setId(1l);
        loanData.setLoanId(UUID.randomUUID());

        // when
        Long id = loanDataServiceRepository.save(loanData).getId();
        List<LoanData> loanDataList = loanDataServiceRepository.findBycustomerId(123456l);

        // then
        assertThat(id).isNotNull();
        assertThat(loanDataList).isNotEmpty();
        assertThat(loanDataList.size()).isEqualTo(1);
    }
}
