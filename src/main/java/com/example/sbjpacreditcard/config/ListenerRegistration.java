package com.example.sbjpacreditcard.config;

import com.example.sbjpacreditcard.listeners.PostLoadListener;
import com.example.sbjpacreditcard.listeners.PreInsertListeners;
import com.example.sbjpacreditcard.listeners.PreUpdateListener;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class ListenerRegistration implements BeanPostProcessor {

    private final PostLoadListener postLoadListener;
    private final PreInsertListeners preInsertListeners;
    private final PreUpdateListener preUpdateListener;

    public ListenerRegistration(PostLoadListener postLoadListener, PreInsertListeners preInsertListeners, PreUpdateListener preUpdateListener) {
        this.postLoadListener = postLoadListener;
        this.preInsertListeners = preInsertListeners;
        this.preUpdateListener = preUpdateListener;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof LocalContainerEntityManagerFactoryBean) {
            LocalContainerEntityManagerFactoryBean lcemf = (LocalContainerEntityManagerFactoryBean) bean;
            SessionFactoryImpl sessionFactory = (SessionFactoryImpl) lcemf.getNativeEntityManagerFactory();
            EventListenerRegistry service = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);

            service.appendListeners(EventType.POST_LOAD, postLoadListener);
            service.appendListeners(EventType.PRE_INSERT, preInsertListeners);
            service.appendListeners(EventType.PRE_UPDATE, preUpdateListener);

        }


        return bean;
    }
}
