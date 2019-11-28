package com.paymentserviceexample.PSE.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.paymentserviceexample.PSE.model.User;

@Repository
public class UserRepository {
	ArrayList<User> users = new ArrayList<User>();
	public User findByEmail(String email){
		for (User u : users){
			if (u.getEmail().equals(email)){
				return u;
			}
		}
		return null;
	}
	public UserRepository() {
		super();
		users.add(new User("a@gmail.com", 10000));
		users.add(new User("b@gmail.com", 5000));
		users.add(new User("c@gmail.com", 1000));
		
		
	}
	
	
}
