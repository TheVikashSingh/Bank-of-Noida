package com.bank.noida.cards.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
public class ResponseDTO {

    @Schema(
            description = "Status Code"
    )
    private String statusCode;

    @Schema(
            description = "Status Message"
    )
    private String statusMsg;
}
