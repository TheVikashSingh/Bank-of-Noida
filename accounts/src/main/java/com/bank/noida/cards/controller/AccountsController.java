package com.bank.noida.cards.controller;


import com.bank.noida.cards.constants.AccountsConstants;
import com.bank.noida.cards.dto.CustomerDTO;
import com.bank.noida.cards.dto.ErrorResponseDTO;
import com.bank.noida.cards.dto.ResponseDTO;
import com.bank.noida.cards.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/api/",produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Tag(
        name = "CRUD ReST APIs for Accounts in Bank of Noida",
        description = "Use Mobile Number to create an account. Only one account" +
                " is allowed for a single mobile number. "
)
public class AccountsController {

    private final IAccountsService iaccountsService;

    @Value("${build.version}")
    private String buildVersion;

    @Operation(
            summary = "Create Account ReST API",
            description = "ReST API to create a new Account in Bank of Noida"  
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP status OK"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDTO.class)
                            )
                    )
            }
            )
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO){
        iaccountsService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }




    @Operation(
            summary = "Get Account ReST API",
            description = "ReST API to show info of an Account in Bank of Noida"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP status OK"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDTO.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<CustomerDTO> getAccountDetails(@RequestParam
                                                             @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a suitable mobile number")
                                                             String mobileNumber){
        CustomerDTO customerDTO = iaccountsService.getAccountDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }




    @Operation(
            summary = "Update Account ReST API",
            description = "ReST API to update an Account in Bank of Noida"
    )
    @ApiResponses(
            {
            @ApiResponse(
                 responseCode = "200",
                 description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )),
            @ApiResponse(
                    responseCode = "417",
                    description = "Update Expectation Failed"
            )
}
)
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDTO customerDTO){
        boolean isUpdated = iaccountsService.updateAccounts(customerDTO);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_UPDATE));
        }
    }




    @Operation(
            summary = "Delete Account ReST API",
            description = "ReST API to delete an Account in Bank of Noida"
    )
    @ApiResponses(
            {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Delete Expectation failed"
            )
    }
    )
    @DeleteMapping("/delete/{mobileNumber}")
    public ResponseEntity<ResponseDTO> deleteAccount(@PathVariable
                                                         @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a suitable mobile number")
                                                         String mobileNumber){
        boolean isDeleted = iaccountsService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_DELETE));
        }
    }




    @Operation(
            summary = "Get Build Information",
            description = "Get Build Information of the Accounts Microservice"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP status OK"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDTO.class)
                            )
                    )
            }
    )
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

}


