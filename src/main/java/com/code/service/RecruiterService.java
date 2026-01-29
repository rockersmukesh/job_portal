package com.code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.code.model.Recruiter;

public interface RecruiterService {

    void saveRecruiter(Recruiter recruiter);

    boolean emailExists(String email);

    Recruiter findByEmail(String email);

    void setCurrentRecruiter(Recruiter recruiter);
    
    Recruiter getCurrentRecruiter();

    List<Recruiter> findAllRecruiters();
    boolean deleteRecruiter(int recruiterId);
    long countRecruiters();
    List<Recruiter> findPendingApprovals();

}
