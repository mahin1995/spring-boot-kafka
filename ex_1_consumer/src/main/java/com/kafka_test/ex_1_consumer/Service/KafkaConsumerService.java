package com.kafka_test.ex_1_consumer.Service;

import com.kafka_test.ex_1_consumer.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Value(value = "${kafka.topic.name}")
    private String topic;
//    @KafkaListener(topics= "${kafka.topic.name}", groupId = "${kafka.group.name}", containerFactory = "myCustomDtoKafkaListenerContainerFactory")
//    public void listen(Customer message) {
//        System.out.println("Received Message: " + message);
//    }

    private final ConcurrentKafkaListenerContainerFactory<String, Customer> factory;

    @Autowired
    public KafkaConsumerService(ConcurrentKafkaListenerContainerFactory<String, Customer> factory) {
        this.factory = factory;
    }

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group.name}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(Customer message) throws InterruptedException {
//        factory.getConsumerFactory().wait();
        System.out.println("Received Message: " + message);
        // Process the message
    }
}
