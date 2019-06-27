package com.mcsjavaprojects.service;

import java.util.List;

import com.mcsjavaprojects.model.UserProfile;


public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
