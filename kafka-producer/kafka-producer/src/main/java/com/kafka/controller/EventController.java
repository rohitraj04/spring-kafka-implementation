package com.kafka.controller;

import com.kafka.dto.Customer;
import com.kafka.dto.User;
import com.kafka.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    @GetMapping("/publish-message/{message}")
    public ResponseEntity<?> fireEvent(@PathVariable("message") String message) {
        try {
            for (int i = 0; i < 5000; i++) {
                kafkaMessagePublisher.sendMessageToTopic(message);
            }
            return ResponseEntity.ok("FIRED SUCCESSFULLY");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PostMapping("/publish-message")
    public ResponseEntity<?> fireCustomerEvent(@RequestBody Customer customer) {
        try {

            kafkaMessagePublisher.sendObjectMessageToTopic(customer);
            return ResponseEntity.ok("FIRED SUCCESSFULLY");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PostMapping("/publish-message-user")
    public ResponseEntity<?> fireCustomerEvent(@RequestBody User user) {
        try {

            kafkaMessagePublisher.sendUserMessageToTopic(user);
            return ResponseEntity.ok("FIRED SUCCESSFULLY");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
