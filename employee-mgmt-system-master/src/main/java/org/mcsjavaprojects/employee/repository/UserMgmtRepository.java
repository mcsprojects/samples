package org.mcsjavaprojects.employee.repository;

import org.mcsjavaprojects.employee.entity.UserMgmt;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author MCS
 *
 */
public interface UserMgmtRepository extends JpaRepository<UserMgmt, Long> {
	public UserMgmt findOneByUsername(String username);	
	
}
