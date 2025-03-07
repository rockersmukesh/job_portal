package com.code.service;

import com.code.model.JobSeeker;
import com.code.repo.JobSeekerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomJobSeekerDetailService implements UserDetailsService {
    private final JobSeekerRepo jobSeekerRepo;

    @Autowired
    public CustomJobSeekerDetailService(JobSeekerRepo jobSeekerRepo) {
        this.jobSeekerRepo = jobSeekerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JobSeeker jobSeeker = jobSeekerRepo.findByEmailId(username).orElseThrow(() -> new UsernameNotFoundException("Could not found user"));
        return new CustomJobSeekerDetailService(jobSeeker);
    }
}
