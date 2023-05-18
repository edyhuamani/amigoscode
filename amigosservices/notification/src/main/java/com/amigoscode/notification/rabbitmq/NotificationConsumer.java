package com.amigoscode.notification.rabbitmq;

import com.amigoscode.clients.notification.NotificationRequest;
import com.amigoscode.notification.services.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    // nombre de cola
    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(String message ){
        try {
            log.info("Consumed {} from queue", message);
            //notificationRequest.getPayload();
            //notificationService.send(notificationRequest.getPayload());
            ObjectMapper mapper = new ObjectMapper();
            NotificationRequest notificationRequest = mapper.readValue(message, NotificationRequest.class);
            notificationService.send(notificationRequest);
        }catch(Exception ex){
            log.error(ex.getMessage(),ex);
        }
    }





}
