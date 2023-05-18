package com.amigoscode.customer.controller;

import com.amigoscode.customer.model.CustomerRegistrationRequest;
import com.amigoscode.customer.service.CustomerService;
import com.amigoscode.customer.service.CustomerServiceB;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public record CustomerController(CustomerService customerService) {

    //@Autowired
    //private CustomerService customerService;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRequest){
        try {
            log.info("new customer registration {}", customerRequest);
            customerService.registerCustomer(customerRequest);
        } catch(Exception ex){
            log.error(ex.getMessage(),ex);
        }
    }
}
