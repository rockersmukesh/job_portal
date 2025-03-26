package com.code.service;

public interface OtpService {
    void saveOtp(String email, String otp);
    boolean verifyOtp(String email, String otp);
}
