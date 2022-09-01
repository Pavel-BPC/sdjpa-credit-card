package com.example.sbjpacreditcard.interseptors;

import org.hibernate.CallbackException;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Service;

@Service
public class EncryptInterceptor implements Interceptor {
    @Override
    public boolean onLoad(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        System.out.println("EncryptInterceptor onLoad");
        return Interceptor.super.onLoad(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onFlushDirty(Object entity, Object id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException {
        System.out.println("EncryptInterceptor onFlushDirty");
        return Interceptor.super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    @Override
    public boolean onSave(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        System.out.println("EncryptInterceptor onSave");
        return Interceptor.super.onSave(entity, id, state, propertyNames, types);
    }
}
