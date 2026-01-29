package com.code.service.impl;

import com.code.model.JobSeeker;
import com.code.repo.JobSeekerRepo;
import com.code.service.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class JobSeekerServiceImpl implements JobSeekerService {

    @Autowired
    private JobSeekerRepo jobSeekerRepo;

    private ConcurrentHashMap<String, String> otpStorage = new ConcurrentHashMap<>();

    @Override
    public void saveOrUpdate(JobSeeker jobSeeker) {
        jobSeekerRepo.save(jobSeeker); // Save or update the job seeker
    }

    @Override
    public JobSeeker findById(int id) {
        return jobSeekerRepo.findById(id).orElse(null);
    }

    @Override
    public JobSeeker save(JobSeeker jobSeeker) {
        return jobSeekerRepo.save(jobSeeker);
    }

    @Override
    public List<JobSeeker> findAllJobSeekers() {
        return jobSeekerRepo.findAll();
    }

    @Override
    public long countJobSeekers() {
        return jobSeekerRepo.count();
    }

    @Override
    public boolean deleteJobSeeker(int jobSeekerId) {
        Optional<JobSeeker> dbJobSeeker = jobSeekerRepo.findById(jobSeekerId);
        if (dbJobSeeker.isPresent()) {
            jobSeekerRepo.delete(dbJobSeeker.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<JobSeeker> findByEmail(String email) {
        return jobSeekerRepo.findByEmailId(email);
    }

    @Override
    public boolean resetPassword(String token, String password) {
        Optional<JobSeeker> jobSeekerOptional = jobSeekerRepo.findByResetToken(token);
        if (jobSeekerOptional.isEmpty()) {
            return false;
        }

        JobSeeker jobSeeker = jobSeekerOptional.get();
        jobSeeker.setPassword(password);
        jobSeeker.setResetToken(null);
        jobSeekerRepo.save(jobSeeker);
        return true;
    }

    @Override
    public String generateOtp(String email) {
        Optional<JobSeeker> jobSeekerOptional = jobSeekerRepo.findByEmailId(email);
        if (jobSeekerOptional.isEmpty()) {
            return null;
        }

        String otp = String.valueOf((int)(Math.random() * 9000) + 1000);
        otpStorage.put(email, otp);
        return otp;
    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        String storedOtp = otpStorage.get(email);
        if (storedOtp != null && storedOtp.equals(otp)) {
            otpStorage.remove(email);
            return true;
        }
        return false;
    }

    @Override
    public String getPasswordByEmail(String email) {
        Optional<JobSeeker> jobSeekerOptional = jobSeekerRepo.findByEmailId(email);
        return jobSeekerOptional.map(JobSeeker::getPassword).orElse(null);
    }
}