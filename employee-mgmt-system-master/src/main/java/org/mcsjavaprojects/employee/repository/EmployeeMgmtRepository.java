package org.mcsjavaprojects.employee.repository;

import org.mcsjavaprojects.employee.entity.EmployeeMgmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMgmtRepository extends JpaRepository<EmployeeMgmt, Long> {
	Object findOneByEmployee(String employee);
} 


