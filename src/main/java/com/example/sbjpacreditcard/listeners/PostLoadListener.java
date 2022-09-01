package com.example.sbjpacreditcard.listeners;

import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.springframework.stereotype.Component;

@Component
public class PostLoadListener extends AbstractEncryptionListener implements PostLoadEventListener {
    @Override
    public void onPostLoad(PostLoadEvent event) {
        System.out.println("on Post Load");
        this.decrypt(event.getEntity());

    }
}
