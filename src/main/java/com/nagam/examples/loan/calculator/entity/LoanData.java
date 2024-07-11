package com.nagam.examples.loan.calculator.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "loanData")
@Data
public class LoanData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loanId", updatable = false)
    @Getter
    private UUID loanId = UUID.randomUUID();

    @Column(name = "customerId",nullable = false)
    private Long customerId;

    @Column(name = "amount")
    private double loanAmount;

    // Can also be implemented with OneToMany, ignored for now. Code is still here for reference purpose.
    /*@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId")
    private Customer customer;*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LoanData loanData = (LoanData) o;

        return id != null && id.equals(loanData.id);
    }

    @Override
    public int hashCode() {
        return 350552062;
    }



}
