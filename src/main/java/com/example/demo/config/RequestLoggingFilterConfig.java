/**
 * 
 */
package com.example.demo.config;

import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Configuration class to display in the log any header received in the request.
 * 
 * @author Daniel Manzano Borja
 * @since 21/05/2025
 * 
 */
@Configuration
@Slf4j
public class RequestLoggingFilterConfig {
	
	private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilterConfig.class);

	@Bean
	Filter customHeaderLoggingFilter() {
		return (request, response, chain) -> {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			log.info("Incoming Request: {} {}", httpRequest.getMethod(), httpRequest.getRequestURI());
			Enumeration<String> headerNames = httpRequest.getHeaderNames();
			if (headerNames != null) {
				while (headerNames.hasMoreElements()) {
					String headerName = headerNames.nextElement();
					String headerValue = httpRequest.getHeader(headerName);
					log.info("Header: {} = {}", headerName, headerValue);
				}
			}
			chain.doFilter(request, response);
		};
	}

}
