package com.bank.noida.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Accounts Information"
)
public class AccountsDTO {

    @Schema(
            description = "Account Number",
            example = "1249860327"
    )
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a suitable mobile number")
    @NotEmpty(message = "Account Number can not be empty")
    private Long accountNumber;


    @Schema(
            description = "Account Type",
            example = "Savings"
    )
    @NotEmpty(message = "Account type must not be empty")
    private String accountType;


    @Schema(
            description = "Bank of Noida Branch's Address",
            example = "Sector 162, Noida"
    )
    @NotEmpty(message = "Branch address must not be empty")
    private String branchAddress;

}
