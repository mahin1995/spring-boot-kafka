package com.kafka_test.ex_1_producer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka_test.ex_1_producer.dto.IDto;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class CustomSerializer<T extends IDto> implements Serializer<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, T data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing DTO", e);
        }
    }
}

