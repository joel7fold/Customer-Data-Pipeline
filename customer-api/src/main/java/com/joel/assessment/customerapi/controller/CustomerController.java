package com.joel.assessment.customerapi.controller;

import com.joel.assessment.customerapi.document.CustomerDocument;
import com.joel.assessment.customerapi.dto.CustomerDto;
import com.joel.assessment.customerapi.dto.PageResponseDto;
import com.joel.assessment.customerapi.producer.CustomerProducer;
import com.joel.assessment.customerapi.service.CustomerMongoService;
import com.joel.assessment.customerapi.service.SnowflakeCustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerProducer customerProducer;
    private final CustomerMongoService customerMongoService;
    private final SnowflakeCustomerService snowflakeCustomerService;

    public CustomerController(CustomerProducer customerProducer,CustomerMongoService customerMongoService,
                              SnowflakeCustomerService snowflakeCustomerService) {
        this.customerProducer = customerProducer;
        this.customerMongoService = customerMongoService;
        this.snowflakeCustomerService = snowflakeCustomerService;
    }

    @GetMapping()
    public ResponseEntity<PageResponseDto<CustomerDto>> getCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(snowflakeCustomerService.findCustomers(page, size));
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<CustomerDto> fetchCustomerById(@PathVariable Long id) {
        CustomerDto customerDto = snowflakeCustomerService.findCustomerById(id);
        customerProducer.send(customerDto);
        return ResponseEntity.ok(customerDto);
    }

    @GetMapping("/mongo")
    public ResponseEntity<List<CustomerDocument>> getCustomersFromMongo() {
        return ResponseEntity.ok(customerMongoService.findAll());
    }

}
