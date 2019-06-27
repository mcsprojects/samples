package com.mcsjavaprojects.dao;

import java.util.List;

import com.mcsjavaprojects.model.User;


public interface UserDao {

	User findById(int id);
	
	User findBySSO(String email);	
	
	void save(User user);
	
	void deleteBySSO(String email);
	
	List<User> findAllUsers();
	
}

