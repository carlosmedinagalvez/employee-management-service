/**
 * 
 */
package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author dmanzano
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -5889859668465687238L;

	public ResourceNotFoundException(String message) {
		super();
	}

}
