package com.kafka.service;

import com.kafka.dto.Customer;
import com.kafka.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {
    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendMessageToTopic(String message) {
        CompletableFuture<SendResult<String, Object>> completableFuture
                = template.send("abc", message);

        completableFuture.whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println("Message sent successfully: " + message + ", with offset : " + result.getRecordMetadata().offset() + " with partition number : " + result.getRecordMetadata().partition());
            } else {
                System.out.println("Exception occured : " + exception.getMessage());
            }
        });
    }

    public void sendObjectMessageToTopic(Customer customer) {
        try {
            CompletableFuture<SendResult<String, Object>> completableFuture
                    = template.send("object-topic-1", customer);

            completableFuture.whenComplete((result, exception) -> {
                if (exception == null) {
                    System.out.println("Message sent successfully: " + customer + ", with offset : " + result.getRecordMetadata().offset() + " with partition number : " + result.getRecordMetadata().partition());
                } else {
                    System.out.println("Exception occured : " + exception.getMessage());
                }
            });
        }
        catch(Exception ee) {
            System.out.println(ee.getMessage());
        }
    }

    public void sendUserMessageToTopic(User user) {
        try {
            CompletableFuture<SendResult<String, Object>> completableFuture
                    = template.send("ram-topic", user);

            completableFuture.whenComplete((result, exception) -> {
                if (exception == null) {
                    System.out.println("Message sent successfully: " + user + ", with offset : " + result.getRecordMetadata().offset() + " with partition number : " + result.getRecordMetadata().partition());
                } else {
                    System.out.println("Exception occured : " + exception.getMessage());
                }
            });
        }
        catch(Exception ee) {
            System.out.println(ee.getMessage());
        }
    }
}
