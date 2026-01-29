package com.code.service.impl;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    private RecruiterRepo recruiterRepo;

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
    Recruiter recruiter = (Recruiter) session.getAttribute("currentRecruiter");
    System.out.println("Recruiter retrieved from session: " + recruiter);
    return recruiter;
}

    @Override
    public void setCurrentRecruiter(Recruiter recruiter) {
        session.setAttribute("currentRecruiter", recruiter);
        System.out.println("Recruiter set in session: " + recruiter);
    }

    @Override
    public List<Recruiter> findAllRecruiters() {
        return recruiterRepository.findAll();
    }

    @Override
    public long countRecruiters() {
        return recruiterRepository.count();
    }

    @Override
    public List<Recruiter> findPendingApprovals() {
        return recruiterRepository.findByApprovalStatus(false);
    }

	@Override
	public boolean deleteRecruiter(int recruiterId) {
		Optional<Recruiter> dbRecruiter = recruiterRepo.findById(recruiterId);
        if (dbRecruiter.isPresent()) {
            recruiterRepo.delete(dbRecruiter.get());
            return true;
        }
        return false;
	}
}
