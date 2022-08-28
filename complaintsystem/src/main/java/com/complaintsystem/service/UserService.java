package com.complaintsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.complaintsystem.model.User;
import com.complaintsystem.model.UserType;
import com.complaintsystem.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repo;

	public User authenticateUser(String username, String password, UserType usertype) {
		return repo.getUserByUsernameAndPaswordAndRole(username, password, usertype);
	}

	public User addUser(User user) {
		user = repo.save(user);
		if (user != null) {
			user = getUserById(user.getId());
		}
		return user;
	}

	public List<User> getAllUsers() {
		return repo.findAll();
	}
	
	public List<User> getAllEngineers() {
		List<User> lu = new ArrayList<User>();
		for(User u : repo.findAll()) {
			if(u.getUsertype() == UserType.ENGINEER) {
				lu.add(u);
			}
		}
		return lu;
	}

	public User getUserById(int id) {
		if (repo.findById(id).isPresent())
			return repo.findById(id).get();
		else
			return null;
	}

	public User updateUser(User user, int id) {
		if (repo.findById(id).isPresent()) {
			User old = repo.findById(id).get();
			old.setName(user.getName());
			old.setEmail(user.getEmail());
			old.setPassword(user.getPassword());
			old.setUsertype(user.getUsertype());
			old.setPhoneno(user.getPhoneno());
			return repo.save(old);
		} else
			return null;
	}
	
	public boolean deleteUser(int id) {
		if (repo.findById(id).isPresent()) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}
}
