package com.bank.noida.cards.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDTO {

    @Schema(
            description = "Customer's name",
            example = "Vikash"
    )
    @NotEmpty(message = "Please enter the name")
    @Size(min = 5, max = 40, message = "Customer name must have length between 5 to 40")
    private String name;


    @Schema(
            description = "Customer's Email",
            example = "vikash@zohomail.com"
    )
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Please enter a valid email")
    private String email;


    @Schema(
            description = "Customer's mobile number",
            example = "9852641207"
    )
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a suitable mobile number")
    private String mobileNumber;


    @Schema(
            description = "Account Details of the customer"
    )
    private AccountsDTO accountsDTO;

}
