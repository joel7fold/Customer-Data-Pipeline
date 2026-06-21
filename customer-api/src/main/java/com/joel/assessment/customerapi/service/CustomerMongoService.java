package com.joel.assessment.customerapi.service;

import com.joel.assessment.customerapi.document.CustomerDocument;
import com.joel.assessment.customerapi.repository.CustomerMongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerMongoService {

    private final CustomerMongoRepository customerMongoRepository;

    public CustomerMongoService(CustomerMongoRepository customerMongoRepository) {
        this.customerMongoRepository = customerMongoRepository;
    }

    public List<CustomerDocument> findAll() {
        return customerMongoRepository.findAll();
    }
}