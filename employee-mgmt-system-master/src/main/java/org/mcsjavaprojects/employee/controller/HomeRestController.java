package org.mcsjavaprojects.employee.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.mcsjavaprojects.employee.entity.UserMgmt;
import org.mcsjavaprojects.employee.repository.UserMgmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author MCS
 *
 */
@RestController
public class HomeRestController {
	@Autowired
	private UserMgmtRepository userRepo;

	/**
	 * Este método se usa para el registro del usuario de la aplicación. Nota: el registro del usuario
	 * no requiere autenticación.	 
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<UserMgmt> createUser(@RequestBody UserMgmt userMgmt) {
		if (userRepo.findOneByUsername(userMgmt.getUsername()) != null) {
			throw new RuntimeException("Username already exist");
		}
		List<String> roles = new ArrayList<>();
		roles.add("USER");
		userMgmt.setRoles(roles);
		return new ResponseEntity<UserMgmt>(userRepo.save(userMgmt), HttpStatus.CREATED);
	}

	/**
	 * Este método retorna el usuario que ha iniciado sesión (logged user).	 
	 */
	@RequestMapping("/user")
	public UserMgmt user(Principal principal) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		return userRepo.findOneByUsername(loggedUsername);
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> login(@RequestParam String username, @RequestParam String password,
			HttpServletResponse response) throws IOException {
		String token = null;
		UserMgmt userMgmt = userRepo.findOneByUsername(username);
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		if (userMgmt != null && userMgmt.getPassword().equals(password)) {
			token = Jwts.builder().setSubject(username).claim("roles", userMgmt.getRoles()).setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
			tokenMap.put("token", token);
			tokenMap.put("user", userMgmt);
			return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.OK);
		} else {
			tokenMap.put("token", null);
			return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.UNAUTHORIZED);
		}

	}
}
