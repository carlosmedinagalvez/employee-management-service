/**
 * 
 */
package com.example.demo.models.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO class that carries employee data. This data travels through the
 * front, business, and persistence layers.
 * 
 * @author Daniel Manzano Borja
 * @since 14-MAY-2025
 *
 */
@Getter
@Setter
public class EmployeeDTO implements Serializable {
		
	private static final long serialVersionUID = 5029520488543747256L;

	private Long id;
	
	@NotBlank(message = "The First Name is required.")
	private String firstName;
	
	private String secondName;
	
	@NotBlank(message = "The Paternal Last Name is required.")
	private String paternalLastName;
	
	@NotBlank(message = "The Maternal Last Name is required.")
	private String maternalLastName;
	
	@NotNull(message = "The age is required.")
	private Integer age;
	
	@NotBlank(message = "The gender is required, must enter the values ​​Male, Female, or Other.")
	private String gender;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@NotNull(message = "The Date of Birth is required.")
	private LocalDate dateOfBirth;
	
	@NotBlank(message = "The position is required.")
	private String position;

}
