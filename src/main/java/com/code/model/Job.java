package com.code.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobId;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private String jobLocation;

    @Column(nullable = false)
    private String jobCompany;

    @Column(nullable = false)
    private String jobType;

    @Column(nullable = false)
    private String jobDescription;

    @Column(nullable = false)
    private String salary; // New field for salary

    @Column(nullable = false)
    private String experienceRequired; // New field for experience

    @Column(nullable = false)
    private String skillsRequired; // New field for skills

    @Column(nullable = false)
    private String jobCategory; // New field for job category

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private Recruiter recruiter;
}
