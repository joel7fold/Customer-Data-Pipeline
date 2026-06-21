package com.joel.assessment.kafkaconsumer.dto;

public record AddressDto(
        String street,
        String number,
        String city,
        String state,
        String country
) {
}