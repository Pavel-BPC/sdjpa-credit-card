package com.example.sbjpacreditcard.listeners;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.stereotype.Component;

@Component
public class PreInsertListeners implements PreInsertEventListener {
    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        System.out.println("on Pre Insert");
        return false;
    }
}
