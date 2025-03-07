package com.code.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name= "jobseeker_details")
@Data
public class JobSeekerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobDetailsId;

    @OneToOne
    @JoinColumn(name = "jobseeker_details_id")
    @MapsId
    private JobSeeker jobSeekerId;

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
