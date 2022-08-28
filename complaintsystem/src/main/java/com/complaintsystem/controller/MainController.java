package com.complaintsystem.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.complaintsystem.model.Complaint;
import com.complaintsystem.model.ComplaintUpdates;
import com.complaintsystem.model.User;
import com.complaintsystem.model.UserType;
import com.complaintsystem.service.ComplaintService;
import com.complaintsystem.service.ComplaintUpdateService;
import com.complaintsystem.service.UserService;

@RestController
public class MainController {

	@Autowired
	UserService userService;
	
	@Autowired
	ComplaintService compService;
	
	@Autowired
	ComplaintUpdateService compUpdateService;
	
	@PostMapping("/login")
	public User authUser(@RequestParam String username,@RequestParam String password,@RequestParam String usertype) {
		System.out.println("Login Params : " + username + "_" + password + "_" + usertype);
		UserType ut = UserType.CUSTOMER;
		if(usertype.equalsIgnoreCase("MANAGER")) {
			ut = UserType.MANAGER;
		} else if(usertype.equalsIgnoreCase("ENGINEER")) {
			ut = UserType.ENGINEER;
		}
		User user = userService.authenticateUser(username, password, ut);
		System.out.println("User : " + user.toString());

		return user;
	}

	@GetMapping("/user")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/user/engineers")
	public List<User> getAllEngineers() {
		return userService.getAllEngineers();
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Object> getUser(@PathVariable int id) {
		User user = userService.getUserById(id);

		if (user != null)
			return new ResponseEntity<Object>(user, HttpStatus.FOUND);
		else
			return new ResponseEntity<Object>("User is not available with given id", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestBody User user) {
		User updatedUser = userService.updateUser(user, id);

		if (updatedUser != null)
			return new ResponseEntity<Object>(updatedUser, HttpStatus.FOUND);
		else
			return new ResponseEntity<Object>("Updated Operation Failed..", HttpStatus.NOT_FOUND);
	}

	@PostMapping("/user")
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		User addedUser = userService.addUser(user);

		if (addedUser != null)
			return new ResponseEntity<Object>(addedUser, HttpStatus.CREATED);
		else
			return new ResponseEntity<Object>("Error while adding user", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		if(userService.deleteUser(id))
			return new ResponseEntity<Object>("User Is Successfully Deleted", HttpStatus.FOUND);
		else
			return new ResponseEntity<Object>("User is not available with given id : " + id, HttpStatus.NOT_FOUND);
	}
	
	// ================================: Complaint Calls :================================
	@PostMapping("/complaint")
	public ResponseEntity<Object> addComplaint(@RequestBody Complaint comp){
		Complaint newComplaint = new Complaint(comp.getSubject(), comp.getDetail(), getUserByObject(comp.getUser()), comp.isResolved(), new Date());

		newComplaint = compService.addComplaint(comp);
		if (newComplaint != null)
			return new ResponseEntity<Object>(newComplaint, HttpStatus.CREATED);
		else
			return new ResponseEntity<Object>("Error while adding complaint.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/complaint")
	public List<Complaint> getAllComplaints(){
		return compService.getAllComplaints();
	}
	
	@GetMapping("/complaints/{userid}")
	public List<Complaint> getUserRoleComplaints(@PathVariable int userid){
		System.out.println("Request User ID : " + userid);
		return compService.getUserRoleComplaints(userid);
	}
	
	@GetMapping("/complaint/{id}")
	public ResponseEntity<Object> getComplaintById(@PathVariable int id){
		Complaint comp = compService.getComplaintById(id);
		
		if (comp != null)
			return new ResponseEntity<Object>(comp, HttpStatus.CREATED);
		else
			return new ResponseEntity<Object>("Error while getting complaint by ID.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// ================================: Complaint Update Calls :================================
	@PostMapping("/complaint/update")
	public ResponseEntity<Object> addComplaintUpdate(@RequestBody ComplaintUpdates compUpdate){
		
		ComplaintUpdates addCompUpdate = new ComplaintUpdates(getComplaintByObject(compUpdate.getComplaint()), getUserByObject(compUpdate.getUser()),
				compUpdate.isWorkingOn(),compUpdate.isResolved(), compUpdate.getStatusRemark(), new Date());
		
		addCompUpdate = compUpdateService.addComplaintUpdates(compUpdate);

		if (addCompUpdate != null)
			return new ResponseEntity<Object>(addCompUpdate, HttpStatus.CREATED);
		else
			return new ResponseEntity<Object>("Error while adding complaint.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/complaint/update")
	public List<ComplaintUpdates> getAllComplaintUpdates(){
		return compUpdateService.getAllComplaintUpdates();
	}
	
	
	// ================================: Other Methods :================================
	public User getUserByObject(User user) {
		User userOb = userService.getUserById(user.getId());
		
		if(userOb == null || userOb.getId()== 0) {
			userOb = new User(user.getName(), user.getEmail(), user.getPassword(), user.getUsertype(), user.getPhoneno());
		}
		return userOb;
	}
	
	public Complaint getComplaintByObject(Complaint comp) {
		Complaint compOb = compService.getComplaintById(comp.getId());
		
		if(compOb == null || compOb.getId()== 0) {
			compOb = new Complaint(comp.getSubject(), comp.getDetail(), getUserByObject(comp.getUser()), comp.isResolved(), new Date());
		}
		return compOb;
	}
	
	// =================================================================================
}
