package com.kafka_test.ex_1_producer.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements IDto {
    private Integer id;
    private String name;
    private String address;
}
