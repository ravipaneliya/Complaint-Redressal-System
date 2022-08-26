package com.complaintsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.complaintsystem.model.ComplaintUpdates;

@Repository
public interface ComplaintUpdateRepository extends JpaRepository<ComplaintUpdates, Integer> {

}
