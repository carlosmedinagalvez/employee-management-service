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
 * @since 12-MAY-2025
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
	 * Method that get a employee by id.
	 * 
	 * @param id | employee Id.
	 * @return {@link EmployeeDTO} | employee object response.
	 */
	EmployeeDTO getEmployeeById(Long id);
	
	/**
	 * Method that save a new employee.
	 * 
	 * @param {@link EmployeeDTO} | Object employeeDTO.
	 * @return {@link EmployeeDTO} | employee object response.
	 */
	List<EmployeeDTO> saveEmployees(List<EmployeeDTO> employeeDTOList);
	
	/**
	 * Method to partially update an employee.
	 * 
	 * @param {id} | Employee ID
	 * @param {@link EmployeeDTO} | employeeDTO object request.
	 * @return {@link EmployeeDTO} | employeeDTO object response.
	 */
	EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDto);
	
	/**
	 * Method that delete an employee by id.
	 * 
	 * @param {id} | Employee ID
	 * @return void
	 */
	boolean deleteEmployee(Long id);

}
