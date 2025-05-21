/**
 * 
 */
package com.example.demo.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
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

import com.example.demo.exceptions.ResourceNotFoundException;
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
 * @since 14/05/2025
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
	@Operation(summary = "Get employee by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee found", content = {
			@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmployeeDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid employee supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content) })
	@GetMapping(path = "/employee/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
		EmployeeDTO employeeDto = new EmployeeDTO();
		try {
			employeeDto = iEmployeeService.getEmployeeById(id);
			if (employeeDto.getId() != 0) {
				return new ResponseEntity<>(employeeDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(employeeDto, HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			log.error("employee-service: ERROR - |{}", ex);
			throw new ResourceNotFoundException("Error: " + HttpStatus.INTERNAL_SERVER_ERROR + ex);
		}
	}

	
	/**
	 * Method getting a list of all existing employees.
	 * 
	 * @return List<{@link EmployeeDTO}> | employee list.
	 */
	@Operation(summary = "Get all employees")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee list found", content = {
			@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmployeeDTO.class)) }),
			@ApiResponse(responseCode = "204", description = "No content", content = @Content) })
	@GetMapping(path = "/employees")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
		List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
		try {
			employeeDTOList = iEmployeeService.getAllEmployees();
			if (!employeeDTOList.isEmpty()) {
				return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(employeeDTOList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception ex) {
			log.error("employee-service: ERROR - |{}", ex);
			throw new ResourceNotFoundException("Error: " + HttpStatus.INTERNAL_SERVER_ERROR + ex);
		}
	}

	
	@Operation(summary = "Inserts one or more employees")
	@ApiResponse(responseCode = "201", description = "Saved employees", content = {
			@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmployeeDTO.class)) })
	@PostMapping(path = "/employees", consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeDTO>> saveEmployees(@Valid @RequestBody List<EmployeeDTO> requestList) {
		List<EmployeeDTO> employeeDtoList = new ArrayList<EmployeeDTO>();
		try {
			employeeDtoList = iEmployeeService.saveEmployees(requestList);
			if (!employeeDtoList.isEmpty()) {
				return new ResponseEntity<>(employeeDtoList, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(employeeDtoList, HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			log.error("employee-service: ERROR - |{}", ex);
			throw new ResourceNotFoundException("Error: " + HttpStatus.INTERNAL_SERVER_ERROR + ex);
		}
	}
	

	@Operation(summary = "Partially updates an employee")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Updated employee", content = {
			@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmployeeDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Id not found", content = @Content) })
	@PatchMapping(path = "/employee/{id}")
	public ResponseEntity<EmployeeDTO> patchEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO request) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		try {
			employeeDTO = iEmployeeService.updateEmployee(id, request);
			return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
		} catch (Exception ex) {
			log.error("employee-service: ERROR - |{}", ex);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@Operation(summary = "Delete an employee by id")
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
