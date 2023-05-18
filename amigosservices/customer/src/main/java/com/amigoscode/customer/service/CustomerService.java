package com.amigoscode.customer.service;

import com.amigoscode.amqp.RabbitMQMessageProducer;
import com.amigoscode.clients.fraud.FraudCheckResponse;
import com.amigoscode.clients.fraud.FraudClient;
import com.amigoscode.clients.notification.NotificationRequest;
import com.amigoscode.customer.model.Customer;
import com.amigoscode.customer.model.CustomerRegistrationRequest;
//import com.amigoscode.customer.model.FraudCheckResponse;
import com.amigoscode.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.management.remote.NotificationResult;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    private CustomerRepository customerRepository;
    //private RestTemplate restTemplate;
    private FraudClient fraudClient;

    /*
    public CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
    }
    */


    //@Autowired
    //private CustomerRepository customerRepository;


    /*
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    */
    public void registerCustomer(CustomerRegistrationRequest request){
        try {
            // request
            //request es el payload
            //customer el objeto a insertar en la bd
            Customer customer = Customer.builder()
                    .firstName(request.firstName())
                    .lastName(request.lastName())
                    .email(request.email())
                    .build();
            // check email valid


            // checl email not taken
            // save on bd
            Customer customer1 =customerRepository.saveAndFlush(customer);

            Integer id=0;
            Map<String,Object> map=new HashMap<>();
            map.put("customerId",customer1.getId());
            /*
            FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                    "http://FRAUD/api/v1/fraud-check/{customerId}"
                    ,FraudCheckResponse.class
                    ,map


            );
            */

            FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer1.getId());


            if (fraudCheckResponse.isFraudster()){
                throw new IllegalAccessException("fraudster");
            }

            NotificationRequest notificacionRequest= new NotificationRequest(
                    customer.getId(),
                    customer.getEmail(),
                    customer.getFirstName()
            );

            rabbitMQMessageProducer.publish(
                    notificacionRequest,
                    "internal.exchange",
                    "internal.notification.routing-key");

        }catch(Exception ex){
            log.error(ex.getMessage(),ex);
        }
    }
}
