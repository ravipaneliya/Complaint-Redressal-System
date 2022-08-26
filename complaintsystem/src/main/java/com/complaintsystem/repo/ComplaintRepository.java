package com.complaintsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.complaintsystem.model.Complaint;;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer>{

}
