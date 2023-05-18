package com.amigoscode.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "notificacion",
        url = "${clients.notification.url}"
)
public interface NotificacionClient {
    @PostMapping("api/v1/notification")
    void sendNotification(NotificationRequest notificationRequest);

}
