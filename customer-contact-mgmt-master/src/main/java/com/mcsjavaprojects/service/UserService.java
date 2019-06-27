package com.mcsjavaprojects.service;

import java.util.List;

import com.mcsjavaprojects.model.Customer;
import com.mcsjavaprojects.model.User;


public interface UserService {
	
	User findById(int id);
	
	User findBySSO(String email);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserBySSO(String email);

	List<User> findAllUsers();	
			
	Customer findCustomerByEmail(String email);	
	
	Customer findCustomerById(int customerId);
	
	List<Customer> findAllCustomers();
	
	void saveCustomer(Customer user); 
	
	void updateCustomer(Customer user);
			
    boolean isCustomerUnique(Integer customerId, String email);
    
	boolean isUserSSOUnique(Integer id, String email);

	void deleteCustomerByEmail(String email);

	
}