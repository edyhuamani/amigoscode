package com.amigoscode.fraud.controller;

import com.amigoscode.fraud.model.FraudCheckResponse;
import com.amigoscode.fraud.service.FraudCheckService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
public class FraudController {

    private final FraudCheckService fraudCheckService;
    /*
    public FraudController(FraudCheckService fraudCheckService) {
        this.fraudCheckService = fraudCheckService;
    }
    */


    @GetMapping("{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId ){
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        FraudCheckResponse fraud = new FraudCheckResponse(isFraudulentCustomer);
        return fraud;
    }
}
