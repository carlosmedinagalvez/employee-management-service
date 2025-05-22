/**
 * 
 */
package com.example.demo.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dto.EmployeeDTO;
import com.example.demo.service.IEmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller class where the employee endpoints are declared.
 * 
 * Controller class is responsible for processing incoming REST API request,
 * preparing a model, and returning the view to be rendered as a response.
 * 
 * @author Daniel Manzano Borja
 * @since 21/05/2025
 * 
 */
@RestController
@RequestMapping(path = "/api", produces = APPLICATION_JSON_VALUE)
@Slf4j
public class EmployeeController {
	
	private IEmployeeService iEmployeeService;

	/**
	 * Constructor to inject the IEmployeeService interface.
	 * 
	 * @param iEmployeeService interface where the signature of the business methods
	 *                         are declared.
	 */
	public EmployeeController(IEmployeeService iEmployeeService) {
		this.iEmployeeService = iEmployeeService;
	}

	/**
	 * Method that get a employee by id.
	 * 
	 * @param id | employee Id.
	 * @return {@link ResponseEntity<EmployeeDTO>} | employee object response.
	 * 
	 */
	@Operation(summary = "Get employee by ID", description = "Returns a specific employee by ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee found", content = {
			@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmployeeDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid employee supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content) })
	@GetMapping(path = "/employee/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
			return new ResponseEntity<>(iEmployeeService.getEmployeeById(id), HttpStatus.OK);
	}


	/**
	 * Method getting the list of all existing employees.
	 * @return  ResponseEntity<List<{@link EmployeeDTO}>> | employee list.
	 */
	@Operation(summary = "List employees", description = "Returns all employees")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee list found", content = {
			@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmployeeDTO.class)) }),
			@ApiResponse(responseCode = "204", description = "No content", content = @Content) })
	@GetMapping(path = "/employees")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
		List<EmployeeDTO> employeeDTOList = iEmployeeService.getAllEmployees();
		if (employeeDTOList.isEmpty()) {
			return new ResponseEntity<>(employeeDTOList, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
	}

	/**
	 * Method to create one or more employees.
	 * @param List<{@link EmployeeDTO}> | Employee list request.
	 * @return List<{@link EmployeeDTO}> | List of employees created.
	 */
	@Operation(summary = "Create employees", description = "Create one or more employees")
	@ApiResponse(responseCode = "201", description = "Employee or employees created successfully", content = {
			@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmployeeDTO.class)) })
	@PostMapping(path = "/employees", consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeDTO>> saveEmployees(@RequestBody List<@Valid EmployeeDTO> requestList) {
		List<EmployeeDTO> employeeDtoList = iEmployeeService.saveEmployees(requestList);
		if (employeeDtoList.isEmpty()) {
			return new ResponseEntity<>(employeeDtoList, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(employeeDtoList, HttpStatus.CREATED);
	}
	

	@Operation(summary = "Partially updates an employee", description = "Update any employee data by ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Updated employee successfully", content = {
			@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmployeeDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Employee ID not found", content = @Content) })
	@PatchMapping(path = "/employee/{id}")
	public ResponseEntity<EmployeeDTO> partialEmployeeUpdate(@PathVariable Long id, @Valid @RequestBody EmployeeDTO request) {
		EmployeeDTO employeeDTO = iEmployeeService.partialEmployeeUpdate(id, request);
		return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
	}
	
	
	@Operation(summary = "Delete employee", description = "Delete an employee by id")
	@ApiResponse(responseCode = "200", description = "Deleted employee")
	@DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
		boolean deleted = iEmployeeService.deleteEmployee(id);
		if (deleted) {
            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
    }

}
