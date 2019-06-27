package com.mcsjavaprojects.dao;

import java.util.List;

import com.mcsjavaprojects.model.Customer;

public interface CustomerDao {
	
    
	void save(Customer user);	
			
	List<Customer> findAllCustomers();

	void deleteCustomerByEmail(String email);	
   
    Customer findCustomerByEmail(String email);
    
    Customer findCustomerById(int customerId);  

	
	    
}


