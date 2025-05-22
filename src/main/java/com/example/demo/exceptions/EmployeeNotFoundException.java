/**
 * 
 */
package com.example.demo.exceptions;

/**
 * @author Daniel Manzano Borja
 * @since 21/05/2025
 *
 */
public class EmployeeNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -5889859668465687238L;

	public EmployeeNotFoundException(Long id) {
		super("Employee with ID " + id + " not found");
	}

}
