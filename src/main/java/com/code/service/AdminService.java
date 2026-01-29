package com.code.service;

import com.code.model.Admin;

public interface AdminService {
    Admin findByEmailAndPassword(String email, String password);
    // void saveAdmin(Admin admin);
    // Admin findById(int adminId);
}
