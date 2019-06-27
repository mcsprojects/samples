package com.mcsjavaprojects.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
public class ReportController {
	
	@Autowired
    UserProfileService userProfileService;
	
	@Autowired
	UserService userService;
		
	
	@RequestMapping(value = {"/customersReport"}, method = RequestMethod.GET)
    public String generateReport(ModelMap model) {
		Customer user = new Customer();
        model.addAttribute("customer", user);     
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "customersReport";
    }    
	
        
    @RequestMapping(value = "/customersReport/report/{fmt}") 	        
    public String report(@PathVariable("fmt") String format, Model model ) {
				
		        model.addAttribute("format", format);
		        model.addAttribute("datasource", userService.findAllCustomers());
		        return "customers_report";
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
