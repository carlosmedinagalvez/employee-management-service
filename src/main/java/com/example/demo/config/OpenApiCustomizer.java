/**
 * 
 */
package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Configuration class defined to customize the entire OpenAPI document. This
 * allows for setting global information, such as contact details, license
 * information, and external documentation.
 * 
 * @author Daniel Manzano Borja
 * @since 21/05/2025
 * 
 */
@Configuration
public class OpenApiCustomizer {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Employee Service API").description("API to manage employees")
				.version("1.0.0").description("This is a sample of an employee catalog.")
				.termsOfService("http://swagger.io/terms/")
				.contact(new Contact().name("Daniel Manzano Borja").email("sandan_77@yahoo.com.mx")
						.url("https://tusitio.com"))
				.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

}
