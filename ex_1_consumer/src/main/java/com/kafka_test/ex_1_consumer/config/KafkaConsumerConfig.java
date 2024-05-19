package com.kafka_test.ex_1_consumer.config;



import com.kafka_test.ex_1_consumer.dto.Customer;
import com.kafka_test.ex_1_consumer.dto.IDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
    @Value(value = "${kafka.group.name}")
    private String group;
    @Bean
    public <T extends IDto> ConsumerFactory<String, T> consumerFactory(Class<T> targetType) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, group);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new CustomDeserializer<>(targetType));
    }

    @Bean
    public <T extends IDto> ConcurrentKafkaListenerContainerFactory<String, T> kafkaListenerContainerFactory(Class<T> targetType) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(targetType));
        return factory;
    }
    @Bean
    public <T extends IDto> ConcurrentKafkaListenerContainerFactory<String, Customer> myCustomDtoKafkaListenerContainerFactory() {
        return  kafkaListenerContainerFactory(Customer.class);
    }
//@Bean
//@ConditionalOnMissingBean(ConsumerFactory.class)
//public  ConsumerFactory<String, Customer> consumerFactory() {
//    Map<String, Object> props = new HashMap<>();
//    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//    props.put(ConsumerConfig.GROUP_ID_CONFIG, group);
//    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class);
//    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new CustomDeserializer<Customer>());
//}
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Customer> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, Customer> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Customer > myCustomDtoKafkaListenerContainerFactory() {
//        return kafkaListenerContainerFactory();
//    }

//    @Bean
//    @ConditionalOnMissingBean(ConsumerFactory.class)
//    public  ConsumerFactory<String, String > consumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, group);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//    @Bean
//    @ConditionalOnMissingBean(name = "kafkaListenerContainerFactory")
//    public  ConcurrentKafkaListenerContainerFactory<String, String > kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String > myCustomDtoKafkaListenerContainerFactory() {
//        return kafkaListenerContainerFactory();
//    }
}
