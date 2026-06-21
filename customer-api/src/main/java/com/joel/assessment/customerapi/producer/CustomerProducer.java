package com.joel.assessment.customerapi.producer;

import com.joel.assessment.customerapi.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerProducer {
    private final KafkaTemplate<String, CustomerDto> kafkaTemplate;
    private final String topicName;

    public CustomerProducer(KafkaTemplate<String, CustomerDto> kafkaTemplate,
                            @Value("${app.kafka.topic}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void send(CustomerDto customerDto) {
        String key = String.valueOf(customerDto.customerId());

        kafkaTemplate.send(topicName, key, customerDto)
                .whenComplete((result, exception) -> {
                    if (exception != null) {
                        System.err.println("Error sending message to Kafka: " + exception.getMessage());
                    } else {
                        System.out.println("Message sent to Kafka topic: " + topicName);
                    }
                });
    }
}