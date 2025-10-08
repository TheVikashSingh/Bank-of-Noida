package com.bank.noida.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Bank of Noida - Accounts Microservice ReST API Documentation",
                description = "RestAPI Documentation for Accounts in Bank of Noida",
                version = "v1",
                contact = @Contact(
                        name = "Vikash Pratap Singh",
                        email = "mailing4vikash@gmail.com",
                        url = "www.vikashpratapsingh.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "www.vikashpratapsingh.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Bank of Noida is one of the finest banks in India.",
                url = "http://localhost:8080/swagger-ui/index.html"
        )
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}




