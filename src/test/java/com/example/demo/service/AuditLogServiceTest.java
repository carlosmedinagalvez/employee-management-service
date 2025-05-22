/**
 * 
 */
package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entity.AuditLog;
import com.example.demo.repository.IAuditLogRepository;

/**
 * 
 */
public class AuditLogServiceTest {
	
	@Mock
    private IAuditLogRepository auditLogRepository;

    @InjectMocks
    private AuditLogService eventLogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogSuccessEvent_savesEventToRepository() {
        // Arrange
        String action = "CREATE";
        String description = "Employee created successfully";

        // Act
        eventLogService.auditLog(action, description);

        // Assert
        ArgumentCaptor<AuditLog> captor = ArgumentCaptor.forClass(AuditLog.class);
        verify(auditLogRepository).save(captor.capture());

        AuditLog savedLog = captor.getValue();
        assertEquals(action, savedLog.getAction());
        assertEquals(description, savedLog.getDescription());
        assertNotNull(savedLog.getDateTime());
    }

    @Test
    void testLogErrorEvent_savesEventToRepository() {
        // Arrange
        String action = "UPDATE";
        String description = "Failed to update employee";

        // Act
        eventLogService.auditLog(action, description);

        // Assert
        ArgumentCaptor<AuditLog> captor = ArgumentCaptor.forClass(AuditLog.class);
        verify(auditLogRepository).save(captor.capture());

        AuditLog savedLog = captor.getValue();
        assertEquals(action, savedLog.getAction());
        assertEquals(description, savedLog.getDescription());
        assertNotNull(savedLog.getDateTime());
    }

}
