/**
 * 
 */
package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.models.dto.EmployeeDTO;
import com.example.demo.service.EmployeeService;

/**
 * 
 */
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
	
	@InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;
	
	private EmployeeDTO employeeDTO;
	private EmployeeDTO employeeDTO1;
	
	@BeforeEach()
	void init() {
		
		employeeDTO = new EmployeeDTO();
		employeeDTO.setFirstName("Esmeralda");
		employeeDTO.setAge(45);
		employeeDTO.setDateOfBirth(LocalDate.now());
		employeeDTO.setGender("Female");
		employeeDTO.setMaternalLastName("Escobar");
		employeeDTO.setPaternalLastName("Martinez");
		employeeDTO.setPosition("Fullstack");
		employeeDTO.setSecondName("");
		employeeDTO.setId(1L);
		
		employeeDTO1 = new EmployeeDTO();
		employeeDTO1.setFirstName("Esmeralda");
		employeeDTO1.setAge(45);
		employeeDTO1.setDateOfBirth(LocalDate.now());
		employeeDTO1.setGender("Female");
		employeeDTO1.setMaternalLastName("Escobar");
		employeeDTO1.setPaternalLastName("Martinez");
		employeeDTO1.setPosition("Fullstack");
		employeeDTO1.setSecondName("");
		employeeDTO1.setId(1L);
		
	}
	
	@Test
    void testGetAllEmployees() {
        List<EmployeeDTO> employees = List.of(employeeDTO);
        when(employeeService.getAllEmployees()).thenReturn(employees);
        ResponseEntity<List<EmployeeDTO>> result = employeeController.getAllEmployees();
        assertEquals(1, result.getBody().size());
        verify(employeeService, times(1)).getAllEmployees();
    }
	
	@Test
    void testGetEmployeeById() {
        when(employeeService.getEmployeeById(1L)).thenReturn(employeeDTO);
        ResponseEntity<EmployeeDTO> response = employeeController.getEmployeeById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTO, response.getBody());
    }
	
	@Test
    void testCreateEmployee() {
		List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();
		employeeList.add(employeeDTO);
		employeeList.add(employeeDTO1);
        when(employeeService.saveEmployees(employeeList)).thenReturn(employeeList);
        ResponseEntity<List<EmployeeDTO>> response = employeeController.saveEmployees(employeeList);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employeeList, response.getBody());
    }
	
	@Test
    void testUpdateEmployee() {
        when(employeeService.updateEmployee(1L, employeeDTO)).thenReturn(employeeDTO);
        ResponseEntity<EmployeeDTO> response = employeeController.patchEmployee(1L, employeeDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTO, response.getBody());
    }
	
	@Test
	void testDeleteEmployee() {
        when(employeeService.deleteEmployee(1L)).thenReturn(true);
        ResponseEntity<String> response = employeeController.deleteEmployeeById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(employeeService, times(1)).deleteEmployee(1L);
    }

}
