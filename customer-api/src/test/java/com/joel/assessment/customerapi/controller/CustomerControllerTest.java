package com.joel.assessment.customerapi.controller;

import com.joel.assessment.customerapi.dto.AddressDto;
import com.joel.assessment.customerapi.dto.CustomerDto;
import com.joel.assessment.customerapi.producer.CustomerProducer;
import com.joel.assessment.customerapi.service.CustomerMongoService;
import com.joel.assessment.customerapi.service.SnowflakeCustomerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Test
    void fetchCustomerByIdShouldSendCustomerToKafka() {
        CustomerProducer customerProducer = mock(CustomerProducer.class);
        CustomerMongoService customerMongoService = mock(CustomerMongoService.class);
        SnowflakeCustomerService snowflakeCustomerService = mock(SnowflakeCustomerService.class);

        CustomerController controller = new CustomerController(
                customerProducer,
                customerMongoService,
                snowflakeCustomerService
        );

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

        when(snowflakeCustomerService.findCustomerById(123L)).thenReturn(customerDto);

        CustomerDto response = controller.fetchCustomerById(123L).getBody();

        assertEquals(123L, response.customerId());
        verify(customerProducer).send(customerDto);
    }
}