package com.code.model;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
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

    @Column
    private String salary; // New field for salary

    @Column(nullable = false)
    private String skillsRequired; // New field for skills

    @Column(nullable = false)
    private String jobCategory; // New field for job category

    @Column(nullable = false)
    private String vacancy;

    @Column
    private String applyUrl;

    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    // private LocalDate validity;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    @ToString.Exclude
    private Recruiter recruiter;
}
