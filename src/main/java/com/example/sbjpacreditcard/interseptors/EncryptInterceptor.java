package com.example.sbjpacreditcard.interseptors;

import com.example.sbjpacreditcard.services.EncryptionService;
import org.hibernate.CallbackException;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EncryptInterceptor implements Interceptor {
    private final EncryptionService encryptionService;

    public EncryptInterceptor(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public boolean onLoad(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        Object[] newState = processFields(entity, state, propertyNames, "onLoad");
        return Interceptor.super.onLoad(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onFlushDirty(Object entity, Object id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException {
        Object[] newState = processFields(entity, currentState, propertyNames, "onFlushDirty");
        return Interceptor.super.onFlushDirty(entity, id, newState, previousState, propertyNames, types);
    }

    @Override
    public boolean onSave(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        Object[] newState = processFields(entity, state, propertyNames, "onSave");
        return Interceptor.super.onSave(entity, id, newState, propertyNames, types);
    }

    private Object[] processFields(Object entity, Object[] state, String[] propertyNames, String type) {
        List<String> annotationFields = getAnnotationFields(entity);
        for (String field : annotationFields) {
            for (int i = 0; i < propertyNames.length; i++) {
                if (propertyNames[i].equals(field)) {
                    if (StringUtils.hasText(state[i].toString())) {
                        if ("onSave".equals(type) || "onFlushDirty".equals(type)) {
                            state[i] = encryptionService.encrypt(state[i].toString());
                        } else if ("onLoad".equals(type)) {
                            state[i] = encryptionService.decrypt(state[i].toString());
                        }
                    }
                }
            }
        }
        return state;
    }

    private List<String> getAnnotationFields(Object entity) {
        List<String> annotationFields = new ArrayList<>();
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (Objects.nonNull(field.getAnnotation(EncryptedString.class))) {
                annotationFields.add(field.getName());
            }
        }
        return annotationFields;
    }


}
