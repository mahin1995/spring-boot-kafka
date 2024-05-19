package com.kafka_test.ex_1_producer.controller;

import com.kafka_test.ex_1_producer.dto.Customer;
import com.kafka_test.ex_1_producer.dto.IDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Value(value = "${kafka.topic.name}")
    private String topic;
    private final KafkaTemplate<String, IDto> kafkaTemplate;

    public KafkaController(KafkaTemplate<String, IDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/publish")
    public void sendMessage(@RequestBody Customer message) {
        for (int i = 0; i < 1000000; i++) {
            message.setId(i);
        kafkaTemplate.send(topic, message);
        }
    }
}