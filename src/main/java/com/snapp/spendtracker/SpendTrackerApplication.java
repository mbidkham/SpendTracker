package com.snapp.spendtracker;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
@OpenAPIDefinition(
    servers = {
        @Server(url = "http://localhost:8080", description = "Local Server URL"),
    }
)
public class SpendTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpendTrackerApplication.class, args);
	}

}
