package org.mcsjavaprojects.employee.controller;

import java.util.List;

import org.mcsjavaprojects.employee.entity.EmployeeMgmt;
import org.mcsjavaprojects.employee.repository.EmployeeMgmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api")
public class EmployeeMgmtRestController {
	
	@Autowired
	private EmployeeMgmtRepository employeeRepo;	 
	
	@Autowired
    ApplicationContext context;	
		
	/**
	 * Servicio web para listar los empleados presentes en la aplicación.	 
	 */
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/employees")	
	public List<EmployeeMgmt> employees() {
		return employeeRepo.findAll();
	}


	/**
	 * Servicio web para obtener un empleado por su ID	 
	 */	
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")	
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeMgmt> employeeById(@PathVariable Long id) {
		EmployeeMgmt employeeMgmt = employeeRepo.findById(id).orElse(null);
		return ResponseEntity.ok().body(employeeMgmt);		
	}

	/**
	 * Método para borrar un empleado por su ID	 
	 */
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@DeleteMapping("/employees/{id}")	
	public ResponseEntity<Void> deleteEmployee(@PathVariable long id) {
		employeeRepo.deleteById(id);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * Método para agregar un empleado
	 * 
	 */
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/employees")
	public ResponseEntity<EmployeeMgmt> createEmployee(@RequestBody EmployeeMgmt employeeMgmt) {
		if (employeeRepo.findOneByEmployee(employeeMgmt.getEmployee()) != null) {
			throw new RuntimeException("El empleado ya existe");
		}
		return new ResponseEntity<EmployeeMgmt>(employeeRepo.save(employeeMgmt), HttpStatus.CREATED);
	}	

	
		

	/**
	 * Método para editar los datos de un empleado	 
	 */
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PutMapping("/employees")
	public EmployeeMgmt updateEmployee(@RequestBody EmployeeMgmt employeeMgmt) {
		if (employeeRepo.findOneByEmployee(employeeMgmt.getEmployee()) != null
				&& ((EmployeeMgmt) employeeRepo.findOneByEmployee(employeeMgmt.getEmployee())).getId() != employeeMgmt.getId()) {
			throw new RuntimeException("El empleado ha sido actualiazado con éxito");
		}
		return employeeRepo.save(employeeMgmt);
	}	
	
	
}
	
	
