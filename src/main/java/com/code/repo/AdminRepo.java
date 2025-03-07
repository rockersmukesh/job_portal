package com.code.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{
	Admin findByEmailIdAndPassword(String emailId, String password);

}
