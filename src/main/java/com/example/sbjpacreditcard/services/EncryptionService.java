package com.example.sbjpacreditcard.services;

public interface EncryptionService {
    String encrypt(String freeText);

    String decrypt(String encryptedText);
}
