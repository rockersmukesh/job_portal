package com.code.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId; 
	
	private String firstName;
	
	private String lastName;
	
	@Column(unique = true, nullable = false)
	private String emailId;
	
	private String password;
	
	private String mobileNo;
	
	@CreationTimestamp
	private Timestamp createdAt;
	
	@UpdateTimestamp
	private Timestamp updatedAt;
}
