package org.mcsjavaprojects.employee.controller;

import java.util.List;

import org.mcsjavaprojects.employee.entity.UserMgmt;
import org.mcsjavaprojects.employee.repository.UserMgmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador Rest para autenticación del usuario y sus datos. Los servicios web de
 * este controlador rest sólo serán accesibles para el usuario con el rol ADMIN
 *  
 */
@RestController
@RequestMapping(value = "/api")
public class UserMgmtRestController {
	@Autowired
	private UserMgmtRepository userRepo;

	/**
	 * Servicio web para obtener un listado de los usuario registrados en la aplicación.	
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<UserMgmt> users() {
		return userRepo.findAll();
	}

	/**
	 * Servicio web para obtener un usuario por su ID	 
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserMgmt> userById(@PathVariable Long id) {
		UserMgmt userMgmt = userRepo.findById(id).orElse(null);
		return ResponseEntity.ok().body(userMgmt);
	}

	/**
	 * Método para borrar un usuario por su ID	 
	 */
	
	@PreAuthorize("hasRole('ROLE_ADMIN'))")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)	
	public ResponseEntity<UserMgmt> deleteUser(@PathVariable long id) {
		userRepo.deleteById(id);
		
		return new ResponseEntity<UserMgmt>(HttpStatus.OK);
	}


	/**
	 * Método para agregar un usuario	 
	 */
		
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<UserMgmt> createUser(@RequestBody UserMgmt userMgmt) {
		if (userRepo.findOneByUsername(userMgmt.getUsername()) != null) {
			throw new RuntimeException("El usuario ya existe");
		}
		return new ResponseEntity<UserMgmt>(userRepo.save(userMgmt), HttpStatus.CREATED);
	}	

	/**
	 * Método para editar los datos del usuario	 
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	public UserMgmt updateUser(@RequestBody UserMgmt userMgmt) {
		if (userRepo.findOneByUsername(userMgmt.getUsername()) != null
				&& userRepo.findOneByUsername(userMgmt.getUsername()).getId() != userMgmt.getId()) {
			throw new RuntimeException("El usuario ya existe");
		}
		return userRepo.save(userMgmt);
	}

}
