package com.joel.assessment.customerapi.repository;

import com.joel.assessment.customerapi.document.CustomerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerMongoRepository extends MongoRepository<CustomerDocument, String> {
}