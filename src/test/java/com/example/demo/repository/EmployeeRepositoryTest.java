/**
 * 
 */
package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.Employee;

/**
 * 
 */
@DataJpaTest
public class EmployeeRepositoryTest {
	
	@Autowired
    private IEmployeeRepository repository;

    @Test
    void testSaveAndFindById() {
    	Employee employee = Employee.builder().firstName("Daniel").age(28).gender("male").position("Engineer").build();
        Employee saved = repository.save(employee);
        Optional<Employee> found = repository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("Daniel", found.get().getFirstName());
    }
    
    @Test
    @DisplayName("Eliminar un empleado por ID")
    void testDeleteById() {
    	Employee employee = Employee.builder().firstName("Esmeralda").age(45).dateOfBirth(LocalDate.now()).gender("Female").maternalLastName("Escobar").paternalLastName("Martinez").position("FullStack").secondName("").id(1L).build();
        Employee saved = repository.save(employee);

        repository.deleteById(saved.getId());
        Optional<Employee> found = repository.findById(saved.getId());

        assertFalse(found.isPresent());
    }

}
