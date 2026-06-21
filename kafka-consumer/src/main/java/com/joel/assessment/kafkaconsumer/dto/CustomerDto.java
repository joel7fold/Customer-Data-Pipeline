package com.joel.assessment.kafkaconsumer.dto;

public record CustomerDto(
        Long customerId,
        String firstName,
        String lastName,
        Integer birthDay,
        Integer birthMonth,
        Integer birthYear,
        String email,
        AddressDto address
) {
}