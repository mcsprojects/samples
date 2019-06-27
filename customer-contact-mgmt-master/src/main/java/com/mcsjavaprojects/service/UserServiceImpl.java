package com.mcsjavaprojects.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcsjavaprojects.dao.CustomerDao;
import com.mcsjavaprojects.dao.UserDao;
import com.mcsjavaprojects.model.Customer;
import com.mcsjavaprojects.model.User;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{  

	@Autowired
	private UserDao dao;
	
	@Autowired
	private CustomerDao customerDao;	

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public User findById(int id) {
		return dao.findById(id);
	}	

	public User findBySSO(String email) {
		User user = dao.findBySSO(email);
		return user;
	}

	public void saveUser(User user) {
		user.setId(user.getId());	
		dao.save(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setEmail(user.getEmail());
			if(!user.getPassword().equals(entity.getPassword())){
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	
	public void deleteUserBySSO(String email) {
		dao.deleteBySSO(email);
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}
		
	
	public boolean isUserSSOUnique(Integer id, String email) {
		User user = findBySSO(email);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}
	
	 public Customer findCustomerByEmail(String email) {
	     return customerDao.findCustomerByEmail(email);
	 }	 
	

	 public boolean isCustomerUnique(Integer customerId, String email) {
	        Customer user = findCustomerByEmail(email);	        
	        return ( user == null ||  ((email != null) && (user.getEmail() == email)));
	       
	 }
	
	 public void saveCustomer(Customer user) {		 
		 user.setCustomerId(user.getCustomerId());			
		 customerDao.save(user);
	 }
	 
	 
	 
	 public void deleteCustomerByEmail(String email) {	        
	        customerDao.deleteCustomerByEmail(email);
	 }
	 
	 public Customer findCustomerById(int customerId) {
	     return customerDao.findCustomerById(customerId);
	 }	 

	@Override
	public List<Customer> findAllCustomers() {
		return customerDao.findAllCustomers();
	}
	
	public void updateCustomer(Customer user) {
		  Customer entity = customerDao.findCustomerById(user.getCustomerId());
		  if(entity!=null){
			entity.setEmail(user.getEmail());				
			entity.setName(user.getName());
			entity.setAddress(user.getAddress());
			entity.setZip(user.getZip());
			entity.setAddress(user.getAddress());
			entity.setContactPerson(user.getContactPerson());
			entity.setPosition(user.getPosition());
			entity.setPhone(user.getPhone());
			entity.setEmail(user.getEmail());
			entity.setLastContact(user.getLastContact());
			entity.setNextContact(user.getNextContact());
			entity.setLeadStatus(user.getLeadStatus());
			entity.setNotes(user.getNotes());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	

	
			
}



