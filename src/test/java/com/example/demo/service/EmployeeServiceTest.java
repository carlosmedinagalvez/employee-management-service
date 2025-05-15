/**
 * 
 */
package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Employee;
import com.example.demo.models.dto.EmployeeDTO;
import com.example.demo.repository.IEmployeeRepository;
import com.example.demo.util.IEmployeeMapper;

/**
 * 
 */
@SpringBootTest
public class EmployeeServiceTest {
	
	@Mock
    private IEmployeeRepository repository;
	
	@Mock
    private IEmployeeMapper mapper;

    @InjectMocks
    private EmployeeService service;
    
    private EmployeeDTO employeeDTO;
	private EmployeeDTO employeeDTO1;
	private Employee employee;
	private Employee employee1;
	
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
		
		employee = new Employee();
		employee.setFirstName("Esmeralda");
		employee.setAge(45);
		employee.setDateOfBirth(LocalDate.now());
		employee.setGender("Female");
		employee.setMaternalLastName("Escobar");
		employee.setPaternalLastName("Martinez");
		employee.setPosition("Fullstack");
		employee.setSecondName("");
		employee.setId(1L);
		
		employee1 = new Employee();
		employee1.setFirstName("Esmeralda");
		employee1.setAge(45);
		employee1.setDateOfBirth(LocalDate.now());
		employee1.setGender("Female");
		employee1.setMaternalLastName("Escobar");
		employee1.setPaternalLastName("Martinez");
		employee1.setPosition("Fullstack");
		employee1.setSecondName("");
		employee1.setId(2L);
		
	}

    @Test
    public void testGetAllEmployees() {
    	List<Employee> mockList = new ArrayList<Employee>();
    	mockList.add(employee);
    	mockList.add(employee1);
        Mockito.when(repository.findAll()).thenReturn(mockList);
        List<EmployeeDTO> result = service.getAllEmployees();
        Assertions.assertEquals(0, result.size());
        Assertions.assertEquals("Esmeralda", result.get(0).getFirstName());
    }
    
    @Test
    void testCreateEmployee() {
    	List<Employee> mockList = new ArrayList<Employee>();
    	mockList.add(employee);
    	mockList.add(employee1);
        when(repository.saveAll(mockList)).thenReturn(mockList);
        List<EmployeeDTO> mockDtoList = new ArrayList<EmployeeDTO>();
        mockDtoList.add(employeeDTO);
        mockDtoList.add(employeeDTO1);
        List<EmployeeDTO> created = service.saveEmployees(mockDtoList);
        assertEquals("Esmeralda", created.get(0).getFirstName());
        verify(repository).saveAll(mockList);
    }
    
    @Test
    void testDeleteEmployee() {
    	doNothing().when(repository).deleteById(1L);
    	service.deleteEmployee(1L);
        verify(repository).findById(1L);
    }

}
