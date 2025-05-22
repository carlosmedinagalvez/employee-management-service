/**
 * 
 */
package com.example.demo.service;

import org.springframework.stereotype.Component;

/**
 * Interface where the signature of the business methods are declared.
 * 
 * @author Daniel Manzano Borja
 * @since 22-MAY-2025
 * 
 */
@Component
public interface IAuditLogService {
	
	/**
	 * Method that records an event in the log with the action, description and current time.
	 * @param action
	 * @param description
	 */
	void auditLog(String action, String description);
	
	

}
