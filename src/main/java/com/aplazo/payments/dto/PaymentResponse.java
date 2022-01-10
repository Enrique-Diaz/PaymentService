package com.aplazo.payments.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * This is a DTO for Payment Response
 *
 * @author Enrique Diaz
 * */
@Data
public class PaymentResponse implements Serializable {

    @ApiModelProperty(allowableValues = "3", notes = "Number of payment based on how many terms where requested.")
    private Integer paymentNumber;
    @ApiModelProperty(allowableValues = "25.5", notes = "Amount to pay each term.")
    private Double amount;
    @ApiModelProperty(allowableValues = "2022-01-17T00:29:38.561+00:00", notes = "Dates on when the payments are going to be cashed.")
    private Date paymentDate;
}