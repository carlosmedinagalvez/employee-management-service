/**
 * 
 */
package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.Employee;
import com.example.demo.exceptions.EmployeeNotFoundException;
import com.example.demo.mapper.IEmployeeMapper;
import com.example.demo.models.dto.EmployeeDTO;
import com.example.demo.repository.IAuditLogRepository;
import com.example.demo.repository.IEmployeeRepository;

/**
 * 
 */
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	
	@Mock
    private IEmployeeRepository employeeRepository;
	
	@Mock
    private IAuditLogRepository auditLogRepository;

    @Mock
    private AuditLogService eventLogService;

    @Mock
    private IEmployeeMapper mapper;

    @InjectMocks
    private EmployeeService service;
	
	@BeforeEach()
	void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
    void testGetEmployeeById_returnsEmployeeDTO() {
        Long id = 1L;
        Employee employee = new Employee();
        employee.setId(id);
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(id);

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        when(mapper.updateDtoFromEmployeeEntity(employee)).thenReturn(dto);

        EmployeeDTO result = service.getEmployeeById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(employeeRepository).findById(id);
    }
	
	@Test
    void testGetEmployeeById_throwsException() {
        Long id = 1L;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> service.getEmployeeById(id));
        verify(employeeRepository).findById(id);
    }
	
	
    @Test
    void testGetAllEmployees_returnsDTOList() {
        List<Employee> entities = List.of(new Employee(), new Employee());
//        List<EmployeeDTO> dtos = List.of(new EmployeeDTO(), new EmployeeDTO());
        when(employeeRepository.findAll()).thenReturn(entities);
        List<EmployeeDTO> result = service.getAllEmployees();
        assertEquals(2, result.size());
        verify(employeeRepository).findAll();
    }
    
    
    @Test
    void testSaveEmployees_returnsSavedList() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setFirstName("Esmeralda");
		employeeDTO.setAge(45);
		employeeDTO.setDateOfBirth(LocalDate.now());
		employeeDTO.setGender("Female");
		employeeDTO.setMaternalLastName("Escobar");
		employeeDTO.setPaternalLastName("Martinez");
		employeeDTO.setPosition("Fullstack");
		employeeDTO.setSecondName("");
		employeeDTO.setId(1L);

        Employee entity = new Employee();
        entity.setFirstName("Esmeralda");
        entity.setAge(45);
        entity.setDateOfBirth(LocalDate.now());
        entity.setGender("Female");
        entity.setMaternalLastName("Escobar");
        entity.setPaternalLastName("Martinez");
        entity.setPosition("Fullstack");
        entity.setSecondName("");
        entity.setId(1L);

        when(mapper.updateEntityFromEmployeeDtoList(List.of(employeeDTO))).thenReturn(List.of(entity));
        when(employeeRepository.saveAll(List.of(entity))).thenReturn(List.of(entity));
        when(mapper.updateDtoFromUserEntityList(List.of(entity))).thenReturn(List.of(employeeDTO));

        List<EmployeeDTO> result = service.saveEmployees(List.of(employeeDTO));

        assertEquals(1, result.size());
        verify(employeeRepository).saveAll(any());
    }
    
    @Test
    void testUpdateEmployee_Success() {
    	Long employeeId = 1L;
        EmployeeDTO dto = new EmployeeDTO();
        dto.setFirstName("Alice");
        dto.setAge(25);
        dto.setGender("female");

        Employee existingEmployee = new Employee();
        existingEmployee.setId(employeeId);

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(employeeId);
        updatedEmployee.setFirstName("Alice");
        updatedEmployee.setAge(25);
        updatedEmployee.setGender("female");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);
        when(mapper.updateDtoFromEmployeeEntity(any(Employee.class))).thenReturn(dto);

        EmployeeDTO result = service.partialEmployeeUpdate(employeeId, dto);

        assertEquals("Alice", result.getFirstName());
        verify(eventLogService, times(1)).auditLog(anyString(), anyString());
        
    }
    
    
    @Test
    void testDeleteEmployee_success() {
        Long id = 1L;
        when(employeeRepository.existsById(id)).thenReturn(true);
        doNothing().when(employeeRepository).deleteById(id);
        boolean deleted = service.deleteEmployee(id);
        assertTrue(deleted);
        verify(employeeRepository).deleteById(id);
    }
    
    @Test
    void testDeleteEmployee_notFound() {
        Long id = 2L;
        when(employeeRepository.existsById(id)).thenReturn(false);

        boolean deleted = service.deleteEmployee(id);

        assertFalse(deleted);
        verify(employeeRepository, never()).deleteById(id);
    }
    
}
