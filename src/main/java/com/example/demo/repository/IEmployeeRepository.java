package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

/**
 * JPA-interface that contains the full API of CRUD Repository.
 * 
 * @author Daniel Manzano Borja
 * @since 14-MAY-2025
 * 
 */
@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

}
