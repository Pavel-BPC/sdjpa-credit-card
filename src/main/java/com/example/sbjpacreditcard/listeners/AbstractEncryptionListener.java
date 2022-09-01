package com.example.sbjpacreditcard.listeners;

import com.example.sbjpacreditcard.interseptors.EncryptedString;
import com.example.sbjpacreditcard.services.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public abstract class AbstractEncryptionListener {
    @Autowired
    private EncryptionService encryptionService;

    public void encrypt(Object[] state, String[] propertyNames, Object entity) {
        ReflectionUtils.doWithFields(entity.getClass(), field -> encryptField(field, state, propertyNames), this::isFieldEncrypted);
    }

    public void decrypt(Object entity) {
        ReflectionUtils.doWithFields(entity.getClass(), field -> decryptField(field, entity), this::isFieldEncrypted);
    }

    private void decryptField(Field field, Object entity) {
        field.setAccessible(true);
        Object value = ReflectionUtils.getField(field, entity);
        ReflectionUtils.setField(field, entity, encryptionService.decrypt(value.toString()));
    }

    private void encryptField(Field field, Object[] state, String[] propertyNames) {
        int idx = getPropertyIndex(field.getName(), propertyNames);
        Object currentValue = state[idx];
        state[idx] = encryptionService.encrypt(currentValue.toString());
    }

    private boolean isFieldEncrypted(Field field) {
        return AnnotationUtils.findAnnotation(field, EncryptedString.class) != null;
    }


    private int getPropertyIndex(String name, String[] propertyNames) {
        for (int i = 0; i < propertyNames.length; i++) {
            if (name.equals(propertyNames[i])) {
                return i;
            }
        }
        throw new IllegalArgumentException("Property not found: " + name);
    }
}
