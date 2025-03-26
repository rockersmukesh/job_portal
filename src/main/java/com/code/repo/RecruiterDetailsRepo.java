package com.code.repo;

import com.code.model.RecruiterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterDetailsRepo extends JpaRepository<RecruiterDetails,Integer> {

}
