package com.code.service;

public interface EmailService {
    void sendPasswordResetEmail(String to, String resetLink);
    void sendOtpEmail(String to, String otp);
    void sendPasswordRetrievalEmail(String to, String password);
}
