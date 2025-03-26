package com.code.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.code.service.OtpService;

@Service
public class OtpServiceImpl implements OtpService{

    private final Map<String, String> otpStorage = new HashMap<>();

    @Override
    public void saveOtp(String email, String otp) {
        otpStorage.put(email, otp);
    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        String storedOtp = otpStorage.get(email);
        return storedOtp != null && storedOtp.equals(otp);
    }
    
}
