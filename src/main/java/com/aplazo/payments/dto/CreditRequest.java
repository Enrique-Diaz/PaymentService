package com.aplazo.payments.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * This is a DTO for Credit Requests
 *
 * @author Enrique Diaz
 * */
@Data
public class CreditRequest implements Serializable {

    @ApiModelProperty(allowableValues = "100.00", notes = "The amount to credit.")
    private Double amount;
    @ApiModelProperty(allowableValues = "5", notes = "The amount of terms to pay the credit, include the first payment.")
    private Integer term;
    @ApiModelProperty(allowableValues = "1.08", notes = "The amount Rate/Charge to cash for the credit.")
    private Double rate;
}