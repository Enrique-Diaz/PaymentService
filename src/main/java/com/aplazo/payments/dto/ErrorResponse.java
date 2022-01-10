package com.aplazo.payments.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * This is a DTO for Error Response
 *
 * @author Enrique Diaz
 * */
@Data
public class ErrorResponse implements Serializable {

    @ApiModelProperty(allowableValues = "ERR_001", notes = "Error Code.")
    private String errorCode;
    @ApiModelProperty(allowableValues = "Missing information", notes = "Message Error.")
    private String errorMessage;

    public ErrorResponse(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}