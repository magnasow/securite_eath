package com.eath.Service;

public interface PasswordResetService {

    boolean initiatePasswordReset(String email);

    boolean verifyToken(String token);

    boolean resetPassword( String token,String newPassword, String confirmPassword);
}
