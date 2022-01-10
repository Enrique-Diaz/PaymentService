package com.aplazo.payments.controller;

import com.aplazo.payments.dto.CreditRequest;
import com.aplazo.payments.dto.ErrorResponse;
import com.aplazo.payments.dto.PaymentResponse;
import com.aplazo.payments.service.IPaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@ApiOperation(value = "Payment Controller")
public class PaymentController {

    @Autowired
    public Logger logger;

    @Autowired
    public IPaymentService iPaymentService;

    /**
     * Rest API to get a list of payments based on amount, rate and terms.
     *
     * @param creditRequest                             Object with credit information.
     * @return ResponseEntity<List<PaymentResponse>>    Response with the payments for given credit.
    * */
    @Operation(summary = "Create a list of payments.", description = "Create payments based on amount, rate and terms.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = Object.class),
            @ApiResponse(code = 201, message = "List of Payments Created", response = PaymentResponse.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> payments(@RequestBody CreditRequest creditRequest) {
        logger.info("Request received {}", creditRequest.toString());

        if (null == creditRequest.getAmount()
                || null == creditRequest.getRate() || null == creditRequest.getTerm()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("ERR_001", "Missing information."));
        }

        logger.info("Getting payments...");
        return ResponseEntity.status(HttpStatus.CREATED).body(iPaymentService.calculatePayments(creditRequest));
    }
}