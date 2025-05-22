/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.models.dto.EmployeeDTO;

/**
 * Interface where the signature of the business methods are declared.
 * 
 * @author Daniel Manzano Borja
 * @since 21-MAY-2025
 * 
 */
@Component
public interface IEmployeeService {
	
	/**
	 * Method getting a list of all existing employees.
	 * 
	 * @return List<{@link EmployeeDTO}> | employee list.
	 */
	List<EmployeeDTO> getAllEmployees();
	
	/**
	 * Method that obtains an employee by ID.
	 * 
	 * @param id | employee ID.
	 * @return {@link EmployeeDTO} | result object response.
	 */
	EmployeeDTO getEmployeeById(Long id);
	
	/**
	 * Method that inserts one or more employees in a single request.
	 * 
	 * @param {@link List<EmployeeDTO>} | employeeDTO list.
	 * @return {@link List<EmployeeDTO>} | employeeDTO list.
	 */
	List<EmployeeDTO> saveEmployees(List<EmployeeDTO> employeeDTOList);
	
	/**
	 * Method to partially update an employee.
	 * 
	 * @param {id} | Employee ID
	 * @param {@link EmployeeDTO} | employeeDTO object request.
	 * @return {@link EmployeeDTO} | employeeDTO object response.
	 */
	EmployeeDTO partialEmployeeUpdate(Long id, EmployeeDTO employeeDto);
	
	/**
	 * Method that delete an employee by id.
	 * 
	 * @param {id} | Employee ID
	 * @return boolean
	 */
	boolean deleteEmployee(Long id);

}
