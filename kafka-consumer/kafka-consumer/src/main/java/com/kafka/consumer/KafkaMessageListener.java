package com.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.dto.Customer;
import com.kafka.dto.User;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class KafkaMessageListener {

    Log log = LogFactory.getLog(this.getClass());

    @KafkaListener(topics = "abc", groupId = "sid")
    public void consume(String message) {
        System.out.println("Consumer consumes1 the message : " + message);

    }

    @KafkaListener(topics = "object-topic-1", groupId = "sid")
    public void consume2(Customer customer) {
        System.out.println("Consumer consumes2 the message : " + customer);

    }

    @RetryableTopic(attempts = "4")// 3 topic N-1
    @KafkaListener(topics = "ram-topic", groupId = "javatechie-group")
    public void consumeEvents(User user, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) throws Exception {
        try {
            log.info("Received: {} from {} offset {}" + new ObjectMapper().writeValueAsString(user) + topic + offset);
            //validate restricted IP before process the records
            List<String> restrictedIpList = Stream.of("32.241.244.236", "15.55.49.164", "81.1.95.253", "126.130.43.183").collect(Collectors.toList());
            if (restrictedIpList.contains(user.getIpAddress())) {
                throw new Exception("Invalid IP Address received !");
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @DltHandler
    public void listenDLT(User user, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("DLT Received : {} , from {} , offset {}" + user.getFirstName() + topic + offset);
    }
}
