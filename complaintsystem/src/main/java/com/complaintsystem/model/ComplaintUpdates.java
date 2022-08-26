package com.complaintsystem.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "complaint_updates")
public class ComplaintUpdates {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "complaint_id")
	private Complaint complaint;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private User user;//assigned engineer's id
	
	// If both are false then again complaint pass to manager to assign another engineer
	private boolean isWorkingOn = true;
	private boolean isResolved = false;
	
	private String statusRemark;
	
	@Column(name = "last_update", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date last_update = new Date();

	public ComplaintUpdates() {}
	
	public ComplaintUpdates(Complaint complaint, User user, boolean isWorkingOn, boolean isResolved, String statusRemark, Date last_update) {
		super();
		this.complaint = complaint;
		this.user = user;
		this.isWorkingOn = isWorkingOn;
		this.isResolved = isResolved;
		this.statusRemark = statusRemark;
		this.last_update = last_update;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isWorkingOn() {
		return isWorkingOn;
	}

	public void setWorkingOn(boolean isWorkingOn) {
		this.isWorkingOn = isWorkingOn;
	}

	public boolean isResolved() {
		return isResolved;
	}

	public void setResolved(boolean isResolved) {
		this.isResolved = isResolved;
	}

	public String getStatusRemark() {
		return statusRemark;
	}

	public void setStatusRemark(String statusRemark) {
		this.statusRemark = statusRemark;
	}

	public Date getLast_update() {
		return last_update;
	}

	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
}
