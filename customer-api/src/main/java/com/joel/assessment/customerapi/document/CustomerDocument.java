package com.joel.assessment.customerapi.document;

import com.joel.assessment.customerapi.dto.AddressDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "customer_data")
public class CustomerDocument {

    @Id
    private String id;

    private Long customerId;
    private String firstName;
    private String lastName;
    private Integer birthDay;
    private Integer birthMonth;
    private Integer birthYear;
    private String email;
    private AddressDto address;
    private Instant receivedAt;

    public CustomerDocument() {
    }

    public CustomerDocument(String id, Long customerId, String firstName, String lastName,
                            Integer birthDay, Integer birthMonth, Integer birthYear,
                            String email, AddressDto address, Instant receivedAt) {
        this.id = id;
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.email = email;
        this.address = address;
        this.receivedAt = receivedAt;
    }

    public String getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getBirthDay() {
        return birthDay;
    }

    public Integer getBirthMonth() {
        return birthMonth;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public String getEmail() {
        return email;
    }

    public AddressDto getAddress() {
        return address;
    }

    public Instant getReceivedAt() {
        return receivedAt;
    }
}