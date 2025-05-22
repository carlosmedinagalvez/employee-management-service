package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AuditLog;

/**
 * JPA-interface that contains the API event log Repository.
 * 
 * @author Daniel Manzano Borja
 * @since 21-MAY-2025
 * 
 */
@Repository
public interface IAuditLogRepository extends JpaRepository<AuditLog, Long> {

}
