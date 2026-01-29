package com.code.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.model.Recruiter;

@Repository
public interface RecruiterRepo extends JpaRepository<Recruiter, Integer> {
    Optional<Recruiter> findByEmailId(String email);
    List<Recruiter> findByApprovalStatus(boolean approvalStatus);
}
