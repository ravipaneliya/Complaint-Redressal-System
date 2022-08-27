package com.complaintsystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.complaintsystem.model.Complaint;
import com.complaintsystem.repo.ComplaintRepository;

@Service
public class ComplaintService {
	
	@Autowired
	ComplaintRepository repo;
	
	public Complaint addComplaint(Complaint comp) {
		comp = repo.save(comp);
		if (comp != null) {
			comp = getComplaintById(comp.getId());
		}
		return comp;
	}

	public List<Complaint> getAllComplaints() {
		return repo.findAll();
	}
	
	public List<Complaint> getUserRoleComplaints(int userid) {
		return repo.findAll();
	}

	public Complaint getComplaintById(int id) {
		if (repo.findById(id).isPresent())
			return repo.findById(id).get();
		else
			return null;
	}
}
