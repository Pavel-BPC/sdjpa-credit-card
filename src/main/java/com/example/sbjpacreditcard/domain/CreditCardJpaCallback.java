package com.example.sbjpacreditcard.domain;

import jakarta.persistence.*;

public class CreditCardJpaCallback {

    @PrePersist
    @PreUpdate
    public void beforeInsertOrUpdate(CreditCard creditCard){
        System.out.println("before Insert Or Update");
    }

    @PostLoad
    @PostUpdate
    @PostPersist
    public void postLoad(CreditCard creditCard){
        System.out.println("post Load");
    }
}
