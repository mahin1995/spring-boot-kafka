package com.kafka_test.ex_1_consumer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka_test.ex_1_consumer.dto.IDto;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class CustomDeserializer<T extends IDto> implements Deserializer<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> targetType;

    public CustomDeserializer(Class<T> targetType) {
        this.targetType = targetType;
    }


    @Override
    public T deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, targetType);
        } catch (Exception e) {
            throw new SerializationException("Error deserializing DTO", e);
        }
    }

}

//public class CustomDeserializer<C> implements Deserializer<Customer> {
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//
//    public CustomDeserializer() {
//
//    }
//
//    @Override
//    public Customer deserialize(String topic, byte[] data) {
//        try {
//            return objectMapper.readValue(data, Customer.class);
//        } catch (Exception e) {
//            throw new SerializationException("Error deserializing DTO", e);
//        }
//    }
//
//}