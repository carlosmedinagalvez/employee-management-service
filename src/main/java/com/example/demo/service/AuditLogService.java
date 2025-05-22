/**
 * 
 */
package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AuditLog;
import com.example.demo.repository.IAuditLogRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Service class where the business methods of audit log operations are
 * implemented.
 *
 * @author Daniel Manzano Borja
 * @since 22-MAY-2025
 */
@Service
@Slf4j
public class AuditLogService implements IAuditLogService {

	private IAuditLogRepository iAuditLogRepository;

	/**
	 * Constructor that injects the iAuditLogRepository interface.
	 * 
	 * @param iAuditLogRepository | JPA-interface that contains the audit log
	 *                            Repository.
	 */
	public AuditLogService(IAuditLogRepository iAuditLogRepository) {
		this.iAuditLogRepository = iAuditLogRepository;
	}

	/**
	 * Method that records an event in the log with the action, description and
	 * current time.
	 * 
	 * @param action
	 * @param description
	 */
	@Override
	public void auditLog(String action, String description) {
		AuditLog auditLog = AuditLog.builder().action(action).description(description).dateTime(LocalDateTime.now())
				.build();
		iAuditLogRepository.save(auditLog);
	}

}
