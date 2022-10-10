package com.cg.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Entity
@Table(name = "deposits")
public class Deposit extends BaseEntity {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
@DecimalMin(value = "50", message = "Số tiền tối thiểu là 50")
@DecimalMax(value = "10000", message = "Số tiền tối đa là 10.000")
    @Column(name = "transaction_amount", precision = 12, scale = 0)
    private BigDecimal transactionAmount;

@ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
public Deposit(){
}

    public Deposit(long id, BigDecimal transactionAmount, Customer customer) {
        this.id = id;
        this.transactionAmount = transactionAmount;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
