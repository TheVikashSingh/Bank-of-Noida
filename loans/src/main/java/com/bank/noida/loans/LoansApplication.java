package com.bank.noida.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.bankofnoida.loans.controller") })
@EnableJpaRepositories("com.bankofnoida.loans.repository")
@EntityScan("com.bankofnoida.loans.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "Bank of Noida Loans microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Vikash Singh",
						email = "bank@bankofnoida.com",
						url = "https://www.bankofnoida.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.bankofnoida.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Bank of Noida Loans microservice REST API Documentation",
				url = "https://www.vikashsingh.com/swagger-ui.html"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}
}
