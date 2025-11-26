package com.example.notesbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point.
 */
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Notes Backend API",
                version = "0.1.0",
                description = "Simple CRUD API for managing notes.",
                contact = @Contact(name = "Notes Backend")
        )
)
public class notesbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(notesbackendApplication.class, args);
    }

}
