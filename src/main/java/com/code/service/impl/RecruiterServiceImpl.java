package com.code.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.model.Recruiter;
import com.code.repo.RecruiterRepo;
import com.code.service.RecruiterService;

import jakarta.servlet.http.HttpSession;

@Service
public class RecruiterServiceImpl implements RecruiterService{
    @Autowired
    private RecruiterRepo recruiterRepository;

    @Autowired
    private HttpSession session;

    @Override
    public void saveRecruiter(Recruiter recruiter) {
        recruiterRepository.save(recruiter);
    }

    @Override
    public boolean emailExists(String email) {
        return recruiterRepository.findByEmailId(email).isPresent();
    }

    @Override
    public Recruiter findByEmail(String email) {
        return recruiterRepository.findByEmailId(email).orElse(null);
    }

    @Override
    public Recruiter getCurrentRecruiter() {
        return (Recruiter) session.getAttribute("currentRecruiter");
    }

    @Override
    public void setCurrentRecruiter(Recruiter recruiter) {
        session.setAttribute("currentRecruiter", recruiter);
    }
}
