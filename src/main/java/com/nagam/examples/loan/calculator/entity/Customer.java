package com.nagam.examples.loan.calculator.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customerId",nullable = false, unique = true)
    private Long customerId;

    @Column(name = "customerName",nullable = false)
    private String customerName;

    // Can also be implemented with OneToMany, ignored for now. Code is still here for reference purpose.
    /*@OneToMany(mappedBy = "customer",fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
    private Set<LoanData> loans;*/
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;

        return id != null && id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return 339958611;
    }




}
