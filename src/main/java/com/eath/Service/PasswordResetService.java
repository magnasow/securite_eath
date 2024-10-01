package com.eath.Service;

public interface PasswordResetService {
    boolean initiatePasswordReset(String email);
    boolean resetPassword(String token, String newPassword);
}
