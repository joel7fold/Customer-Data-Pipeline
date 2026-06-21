package com.joel.assessment.kafkaconsumer.consumer;

import com.joel.assessment.kafkaconsumer.dto.AddressDto;
import com.joel.assessment.kafkaconsumer.dto.CustomerDto;
import com.joel.assessment.kafkaconsumer.service.CustomerMongoService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CustomerConsumerTest {

    @Test
    void consumeShouldSaveCustomerIntoMongo() {
        CustomerMongoService customerMongoService = mock(CustomerMongoService.class);

        CustomerConsumer consumer = new CustomerConsumer(customerMongoService);

        CustomerDto customerDto = new CustomerDto(
                123L,
                "Jane",
                "Doe",
                12,
                8,
                1988,
                "jane.doe@example.com",
                new AddressDto("Main St", "456", "Springfield", "TX", "USA")
        );

        consumer.consume(customerDto);

        verify(customerMongoService).save(customerDto);
    }
}