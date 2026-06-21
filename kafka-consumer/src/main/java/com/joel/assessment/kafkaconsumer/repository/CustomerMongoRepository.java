package com.joel.assessment.kafkaconsumer.repository;

import com.joel.assessment.kafkaconsumer.document.CustomerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerMongoRepository extends MongoRepository<CustomerDocument, String> {
}