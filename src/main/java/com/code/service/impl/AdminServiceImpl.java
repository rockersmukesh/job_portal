package com.code.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.model.Admin;
import com.code.repo.AdminRepo;
import com.code.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepo adminRepo;

    @Override
    public Admin findByEmailAndPassword(String email, String password) {
        return adminRepo.findByEmailIdAndPassword(email, password);
    }

    // @Override
    // public void saveAdmin(Admin admin) {
    //     adminRepository.save(admin);
    // }

    // @Override
    // public Admin findById(int adminId) {
    //     return adminRepository.findById(adminId).orElse(null);
    // }
}
