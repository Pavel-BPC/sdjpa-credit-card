package com.example.sbjpacreditcard.domain;

import com.example.sbjpacreditcard.config.SpringContextHelper;
import com.example.sbjpacreditcard.services.EncryptionService;
import jakarta.persistence.*;

public class CreditCardJpaCallback {

    @PrePersist
    @PreUpdate
    public void beforeInsertOrUpdate(CreditCard creditCard) {
        System.out.println("before Insert Or Update");
        creditCard.setCreditCardNumber(getEncryptionService().encrypt(creditCard.getCreditCardNumber()));
    }

    @PostLoad
    @PostUpdate
    @PostPersist
    public void postLoad(CreditCard creditCard) {
        System.out.println("post Load");
        creditCard.setCreditCardNumber(getEncryptionService().decrypt(creditCard.getCreditCardNumber()));
    }

    private EncryptionService getEncryptionService() {
        return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }
}
