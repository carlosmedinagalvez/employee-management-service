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
 * 
 */
@Configuration
public class OpenApiCustomizer {
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Employee API")
                .description("API para gestionar empleados")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Nombre")
                    .email("tu.email@ejemplo.com")
                    .url("https://tusitio.com"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://springdoc.org")));
    }
}
