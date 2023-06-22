package edu.uoc.epcsd.notification.controllers;

import edu.uoc.epcsd.notification.kafka.KafkaClassListener;
import edu.uoc.epcsd.notification.pojos.Show;
import edu.uoc.epcsd.notification.services.NotificationService;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Response;

@Log4j2
@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void getNotifications(@PathVariable Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String resource = "http://localhost:18081/shows/id/" + id;
        Show response = restTemplate.getForObject(resource, Show.class);
        notificationService.notifyShowCreation(response);
    }

}
