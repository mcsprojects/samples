package org.mcsjavaprojects.employee.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/** 
 * @author M.C.S 
 */
@Configurable
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {	    
    

	// Este método modifica la configuración de WebSecurity	
	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring()
				// ignoring the "/", "/index.html", "/app/**", "/register",
				// "/favicon.ico"
				.antMatchers("/", "/index.html", "/app/**", "/register", "/authenticate", "/report", "/jasper/**");
	}

	
	// En este método se especifican los criterios de autorización para el acceso 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http				
				.authorizeRequests()				
				.anyRequest().fullyAuthenticated().and()
				// Añadiendo filtro JWT 
				.addFilterBefore(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)
				// Habilitando la autenticación básica
				.httpBasic().and()
				// Configurando la sesión como state less			
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()				
				.csrf().disable();
	}

}
