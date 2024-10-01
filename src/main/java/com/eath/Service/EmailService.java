package com.eath.Service;

public interface EmailService {
    void sendPasswordResetEmail(String to, String token);
}
