package com.mcsjavaprojects.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.mcsjavaprojects.security.CustomSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;
	
	@Autowired
	CustomSuccessHandler customSuccessHandler;
		
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}
		
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	    authenticationProvider.setUserDetailsService(userDetailsService);
	    authenticationProvider.setPasswordEncoder(passwordEncoder());
	    return authenticationProvider;
	}
	
	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}

	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 CharacterEncodingFilter filter = new CharacterEncodingFilter();
	     filter.setEncoding("UTF-8");
	     filter.setForceEncoding(true);
	     http.addFilterBefore(filter,CsrfFilter.class);
           
		http.authorizeRequests()
		.antMatchers("/", "/home")
		.access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
		.antMatchers("/home/**", "/addcustomer/**", "/delete-customer-*").access("hasRole('USER')").antMatchers("/customersReport/**", "/edit-customer-*")
		.access("hasRole('USER')")		
		.antMatchers("/admin/**", "/newuser/**", "/delete-user-*").access("hasRole('ADMIN')")
		.antMatchers("/db/**", "/edit-user-*").access("hasRole('ADMIN') or hasRole('DBA')")
		.and().formLogin().loginPage("/login").successHandler(customSuccessHandler)	    
		.loginProcessingUrl("/login").usernameParameter("email").passwordParameter("password")	  	
	  	.and().csrf()
	  	.and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}
	
		
}

