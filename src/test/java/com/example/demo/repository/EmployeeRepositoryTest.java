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
    	Employee employee = new Employee();
        employee.setFirstName("Daniel");
        employee.setAge(28);
        employee.setGender("male");
        employee.setPosition("Engineer");

        Employee saved = repository.save(employee);
        Optional<Employee> found = repository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("Daniel", found.get().getFirstName());
    }
    
    @Test
    @DisplayName("Eliminar un empleado por ID")
    void testDeleteById() {
    	Employee employee = new Employee();
		employee.setFirstName("Esmeralda");
		employee.setAge(45);
		employee.setDateOfBirth(LocalDate.now());
		employee.setGender("Female");
		employee.setMaternalLastName("Escobar");
		employee.setPaternalLastName("Martinez");
		employee.setPosition("Fullstack");
		employee.setSecondName("");
		employee.setId(1L);

        Employee saved = repository.save(employee);

        repository.deleteById(saved.getId());
        Optional<Employee> found = repository.findById(saved.getId());

        assertFalse(found.isPresent());
    }

}
