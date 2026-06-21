package com.joel.assessment.kafkaconsumer.consumer;

import com.joel.assessment.kafkaconsumer.dto.CustomerDto;
import com.joel.assessment.kafkaconsumer.service.CustomerMongoService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerConsumer {

    private final CustomerMongoService customerMongoService;

    public CustomerConsumer(CustomerMongoService customerMongoService) {
        this.customerMongoService = customerMongoService;
    }

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CustomerDto customerDto) {
        System.out.println("Message received from Kafka: " + customerDto);

        customerMongoService.save(customerDto);

        System.out.println("Customer saved into MongoDB: " + customerDto.customerId());
    }
}