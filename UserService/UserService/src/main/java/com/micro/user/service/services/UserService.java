package com.micro.user.service.services;

import java.util.List;

import com.micro.user.service.entities.User;

public interface UserService {
	//Create user
	User saveUser(User user) ;
	//Get all user
	List<User> getAllUser();
	//Get single user
	User getUser(String userId);
	

}
