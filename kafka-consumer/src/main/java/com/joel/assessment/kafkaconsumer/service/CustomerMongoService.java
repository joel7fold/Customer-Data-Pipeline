package com.joel.assessment.kafkaconsumer.service;

import com.joel.assessment.kafkaconsumer.document.CustomerDocument;
import com.joel.assessment.kafkaconsumer.dto.CustomerDto;
import com.joel.assessment.kafkaconsumer.repository.CustomerMongoRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CustomerMongoService {

    private final CustomerMongoRepository customerMongoRepository;

    public CustomerMongoService(CustomerMongoRepository customerMongoRepository) {
        this.customerMongoRepository = customerMongoRepository;
    }

    public CustomerDocument save(CustomerDto customerDto) {
        CustomerDocument document = new CustomerDocument(
                customerDto.customerId(),
                customerDto.firstName(),
                customerDto.lastName(),
                customerDto.birthDay(),
                customerDto.birthMonth(),
                customerDto.birthYear(),
                customerDto.email(),
                customerDto.address(),
                Instant.now()
        );

        return customerMongoRepository.save(document);
    }
}