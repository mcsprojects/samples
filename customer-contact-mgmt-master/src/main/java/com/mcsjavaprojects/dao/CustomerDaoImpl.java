package com.mcsjavaprojects.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mcsjavaprojects.model.Customer;


@Repository("customerDao")
public class CustomerDaoImpl extends AbstractDao<Integer, Customer> implements CustomerDao {
	
	static final Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);
	
		
	public Customer findCustomerById(int customerId) {
		Customer user = getByKey(customerId);
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}
	
		
	
    public Customer findCustomerByEmail(String email) { 
	    logger.info("email : {}", email);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("email", email));
        Customer user = (Customer) crit.uniqueResult();
        if (user != null) {
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }    
	       
   
	public void save(Customer user) { 		
    	persist(user);
    	flush();
    	
	}


	@SuppressWarnings("unchecked")
	public List<Customer> findAllCustomers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Customer> customers = (List<Customer>) criteria.list();
		
		return customers;
	}
    
   
	 public void deleteCustomerByEmail(String email) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("email", email));
		Customer user = (Customer)crit.uniqueResult();
		delete(user);
	}
	

}
