package com.bank.noida.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "Error Response",
        description = "Schema to hold error response information"
)
public class ErrorResponseDTO {

    @Schema(
            description = "API Path invoked by the Client"
    )
    private String apiPath;


    @Schema(
            description = "Error Code"
    )
    private HttpStatus errorCode;


    @Schema(
            description = "Error Message"
    )
    private String errorMsg;


    @Schema(
            description = "Time at which the error occurred"
    )
    private LocalDateTime errorTime;
}
