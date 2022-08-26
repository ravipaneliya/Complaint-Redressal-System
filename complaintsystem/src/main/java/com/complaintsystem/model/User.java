package com.complaintsystem.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;
	private String email;
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserType usertype = UserType.CUSTOMER;

	@Column(name = "phoneno", length = 10)
	private long phoneno;

	@Column(name = "last_update", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date last_update = new Date();

	public User() {}

	public User(String name, String email, String password, UserType usertype, long phoneno) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.usertype = usertype;
		this.phoneno = phoneno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUsertype() {
		return usertype;
	}

		public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}

	public long getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(long phoneno) {
		this.phoneno = phoneno;
	}

	public Date getLast_update() {
		return last_update;
	}

	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", usertype="
				+ usertype + ", phoneno=" + phoneno + ", last_update=" + last_update + "]";
	}
}
