/**
 * 
 */
package com.example.demo.models.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class that carries employee data. This data travels through the
 * front, business, and persistence layers.
 * 
 * @author Daniel Manzano Borja
 * @since 21-MAY-2025
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO implements Serializable {
		
	private static final long serialVersionUID = 5029520488543747256L;
	
	private Long id;

	@NotBlank(message = "The First Name is required")
	@Size(min = 2, max = 50, message = "The First Name must be between 2 and 50 characters")
	private String firstName;
	
	private String secondName;
	
	@NotBlank(message = "The Paternal Last Name is required.")
	@Size(max = 30, message = "The Paternal Last Name must not exceed 30 characters")
	private String paternalLastName;
	
	@NotBlank(message = "The Maternal Last Name is required.")
	@Size(max = 30, message = "The Maternal Last Name must not exceed 30 characters")
	private String maternalLastName;
	
	@NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be at least 18")
	private Integer age;
	
	@NotBlank(message = "Gender is required")
	@Pattern(regexp = "male|female|other", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Gender must be male, female, or other")
	private String gender;
	
	@NotNull(message = "The Date of Birth is required")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;
	
	@NotBlank(message = "The position is required.")
	@Size(max = 30, message = "The Position must not exceed 30 characters")
	private String position;

}
