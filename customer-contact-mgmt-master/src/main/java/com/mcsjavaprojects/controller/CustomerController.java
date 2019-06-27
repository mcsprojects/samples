package com.mcsjavaprojects.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mcsjavaprojects.model.Customer;
import com.mcsjavaprojects.model.UserProfile;
import com.mcsjavaprojects.service.UserProfileService;
import com.mcsjavaprojects.service.UserService;

/**
* @author M. C. S.
*/

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class CustomerController {
	
	@Autowired
    UserProfileService userProfileService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MessageSource messageSource;
	
	/**
	 * This method gets a list of all customers
	 */
	
	@RequestMapping(value = "/customerslist", method = RequestMethod.GET)
	public String listCustomers(ModelMap model) {

		List<Customer> customers = userService.findAllCustomers();
		model.addAttribute("customers", customers);
		model.addAttribute("loggedinuser", getPrincipal());		
		return "customerslist";
	}	
	
	
	/**
	 * This method updates an existing customer.
	 */
	@RequestMapping(value = "/edit-customer-{email}", method = RequestMethod.GET)
	public String editCustomer(@PathVariable String email, ModelMap model) {
		Customer user = userService.findCustomerByEmail(email);
		model.addAttribute("customer", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "customerregistration";
	}
		
	@RequestMapping(value = "/edit-customer-{email}", method = RequestMethod.POST)
	public String updateUser(@Valid Customer user, BindingResult result,
			ModelMap model, @PathVariable String email) {

		if (result.hasErrors()) {
			return "customerregistration";
		}
		
		userService.updateCustomer(user);

		model.addAttribute("success", "Customer " + user.getName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "customerregsuccess";
	}	 
	
	/**
	 * This method adds a new customer.
	 */
    @RequestMapping(value = {"/addcustomer"}, method = RequestMethod.GET)
    public String saveCustomer(ModelMap model) {
        Customer user = new Customer();
        model.addAttribute("customer", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "customerregistration";
    }	
    
   	@RequestMapping(value = "/addcustomer", method = RequestMethod.POST)	 
    public String saveCustomer(@Valid Customer user, BindingResult result,
            ModelMap model) {

        if (result.hasErrors()) {
            return "customerregistration";
        }    
        
        
        if (!userService.isCustomerUnique(user.getCustomerId(), user.getEmail())) {
            FieldError ssoError = new FieldError("Customer", "email", messageSource.getMessage("non.unique.ssoId",
                    new String[]{user.getEmail()}, Locale.getDefault()));          
            result.addError(ssoError);
            
            model.addAttribute("loggedinuser", getPrincipal());
            return "customerregistration";
        }


        userService.saveCustomer(user);

        model.addAttribute("success", "Customer " + user.getName() + " registered successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        //return "success";
        return "customerregsuccess";
    }
	
	
	/**
	 * This method deletes a customer by it's Email value.
	 */
	@RequestMapping(value = "/delete-customer-{email}", method = RequestMethod.GET)
	public String deleteCustomer(@PathVariable String email) {
		userService.deleteCustomerByEmail(email);
		return "redirect:/customerslist";
	}
	
	private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
	
	
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}
	
 
}
