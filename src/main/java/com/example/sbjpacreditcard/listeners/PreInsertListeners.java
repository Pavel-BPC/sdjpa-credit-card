package com.example.sbjpacreditcard.listeners;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.stereotype.Component;

@Component
public class PreInsertListeners extends AbstractEncryptionListener implements PreInsertEventListener {
    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        System.out.println("on Pre Insert");
        this.encrypt(event.getState(), event.getPersister().getPropertyNames(), event.getEntity());
        return false;
    }
}
