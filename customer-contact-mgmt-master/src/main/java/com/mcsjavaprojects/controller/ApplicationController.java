package com.mcsjavaprojects.controller;

import java.util.List;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mcsjavaprojects.model.User;
import com.mcsjavaprojects.model.UserProfile;
import com.mcsjavaprojects.service.UserProfileService;
import com.mcsjavaprojects.service.UserService;

@SessionAttributes("roles")
@Controller

public class ApplicationController {
	
	@Autowired
	UserService userService;
			
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;	
	
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "user";
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "user";
	}
  
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "admin";
	}

	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String dbaPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "dba";
	}
	
	/**
	 * This method gets a list of all users 
	 */
	@RequestMapping(value = "/userslist", method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
		return "userslist";
	}
					
	/**
	 * This method adds a new user.
	 */
	@RequestMapping(value =  "/newuser", method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false); 
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}
	

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}
		
		if(!userService.isUserSSOUnique(user.getId(), user.getEmail())){
			FieldError ssoError =new FieldError("user","email",messageSource.getMessage("non.unique.ssoId", new String[]{user.getEmail()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}
		
		userService.saveUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		//return "success";
		return "registrationsuccess";
	}		
	
	
	/**
	 * This method updates an existing user.
	 */
	@RequestMapping(value = "/edit-user-{email}", method = RequestMethod.GET)
	public String editUser(@PathVariable String email, ModelMap model) {
		User user = userService.findBySSO(email);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = "/edit-user-{email}", method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable String email) {

		if (result.hasErrors()) {
			return "registration";
		}
		
		if(!userService.isUserSSOUnique(user.getId(), user.getEmail())){
			FieldError ssoError =new FieldError("user","email",messageSource.getMessage("non.unique.ssoId", new String[]{user.getEmail()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}


		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationsuccess";
	}
	
	/**
	 * This method deletes an user by it's Email value.
	 */
	@RequestMapping(value = "/delete-user-{email}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable String email) {
		userService.deleteUserBySSO(email);
		return "redirect:/userslist";
	}
		
	/**
	 * This method provides UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}
	
	
	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}
	
	/**
	 * This method handles login GET requests.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
	/**
	 * This method handles logout requests.
	 */	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (auth != null) {
	    new SecurityContextLogoutHandler().logout(request, response, auth);
	  }
	  return "redirect:/login?logout";
	}
	  
	
	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	/**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	@SuppressWarnings("unused")
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}	
	
	
}
