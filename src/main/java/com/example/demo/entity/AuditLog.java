/**
 * 
 */
package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Event log class represents the data model for event log.
 * 
 * @author Daniel Manzano Borja
 * @since 21-MAY-2025	
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "auditlog")
public class AuditLog implements Serializable {
	
	private static final long serialVersionUID = -3180524283655307295L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="action")
	private String action;
	
	@Column(name="description")
	private String description;
	
	@Column(name="dateTime")
	private LocalDateTime dateTime;

}
