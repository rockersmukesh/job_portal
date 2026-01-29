package com.code.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name= "jobseeker_details")
@Data
@Getter
@Setter
public class JobSeekerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobDetailsId;

    @OneToOne
    @JoinColumn(name = "job_seeker_id")
    @MapsId
    private JobSeeker jobSeeker;

    private String city;
    private String state;
    private String country;
    private String workAuthorization;
    private String employmentType;
    private String resume;

    @Column(nullable = true, length = 64)
    private String profilePhoto;

    @OneToMany(targetEntity = Skills.class, cascade = CascadeType.ALL, mappedBy = "jobSeekerDetails")
    private List<Skills> skills;
}
