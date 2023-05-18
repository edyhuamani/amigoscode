package com.amigoscode.customer.service;

import com.amigoscode.customer.model.Customer;
import com.amigoscode.customer.model.CustomerRegistrationRequest;
import com.amigoscode.customer.repository.CustomerRepository;


public record CustomerServiceB() {
    public void registerCustomer(CustomerRegistrationRequest request){
        // request
        //request es el payload
        //customer el objeto a insertar en la bd
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // check email valid
        //
        // save on bd
        //customerRepository.save(customer);
    }
}
