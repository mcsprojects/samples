package com.mcsjavaprojects.dao;

import java.util.List;

import com.mcsjavaprojects.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
	
	UserProfile findCustomerById(int customerId);
	
}
