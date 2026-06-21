package com.joel.assessment.customerapi.service;

import com.joel.assessment.customerapi.dto.AddressDto;
import com.joel.assessment.customerapi.dto.CustomerDto;
import com.joel.assessment.customerapi.dto.PageResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnowflakeCustomerService {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<CustomerDto> customerRowMapper = (rs, rowNum) -> new CustomerDto(
            rs.getLong("CUSTOMER_ID"),
            rs.getString("FIRST_NAME"),
            rs.getString("LAST_NAME"),
            rs.getInt("BIRTH_DAY"),
            rs.getInt("BIRTH_MONTH"),
            rs.getInt("BIRTH_YEAR"),
            rs.getString("EMAIL"),
            new AddressDto(
                    rs.getString("STREET"),
                    rs.getString("STREET_NUMBER"),
                    rs.getString("CITY"),
                    rs.getString("STATE"),
                    rs.getString("COUNTRY")
            )
    );

    public SnowflakeCustomerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PageResponseDto<CustomerDto> findCustomers(int page, int size) {
        int offset = page * size;

        String sql = """
                SELECT
                    C.C_CUSTOMER_SK AS CUSTOMER_ID,
                    C.C_FIRST_NAME AS FIRST_NAME,
                    C.C_LAST_NAME AS LAST_NAME,
                    C.C_BIRTH_DAY AS BIRTH_DAY,
                    C.C_BIRTH_MONTH AS BIRTH_MONTH,
                    C.C_BIRTH_YEAR AS BIRTH_YEAR,
                    C.C_EMAIL_ADDRESS AS EMAIL,
                    CA.CA_STREET_NAME AS STREET,
                    CA.CA_STREET_NUMBER AS STREET_NUMBER,
                    CA.CA_CITY AS CITY,
                    CA.CA_STATE AS STATE,
                    CA.CA_COUNTRY AS COUNTRY
                FROM CUSTOMER C
                LEFT JOIN CUSTOMER_ADDRESS CA
                    ON C.C_CURRENT_ADDR_SK = CA.CA_ADDRESS_SK
                ORDER BY C.C_CUSTOMER_SK
                LIMIT ? OFFSET ?
                """;

        List<CustomerDto> customers = jdbcTemplate.query(sql, customerRowMapper, size, offset);

        Long totalElements = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM CUSTOMER",
                Long.class
        );

        long total = totalElements != null ? totalElements : 0;
        int totalPages = (int) Math.ceil((double) total / size);

        return new PageResponseDto<>(
                customers,
                page,
                size,
                total,
                totalPages
        );
    }

    public CustomerDto findCustomerById(Long id) {
        String sql = """
                SELECT
                    C.C_CUSTOMER_SK AS CUSTOMER_ID,
                    C.C_FIRST_NAME AS FIRST_NAME,
                    C.C_LAST_NAME AS LAST_NAME,
                    C.C_BIRTH_DAY AS BIRTH_DAY,
                    C.C_BIRTH_MONTH AS BIRTH_MONTH,
                    C.C_BIRTH_YEAR AS BIRTH_YEAR,
                    C.C_EMAIL_ADDRESS AS EMAIL,
                    CA.CA_STREET_NAME AS STREET,
                    CA.CA_STREET_NUMBER AS STREET_NUMBER,
                    CA.CA_CITY AS CITY,
                    CA.CA_STATE AS STATE,
                    CA.CA_COUNTRY AS COUNTRY
                FROM CUSTOMER C
                LEFT JOIN CUSTOMER_ADDRESS CA
                    ON C.C_CURRENT_ADDR_SK = CA.CA_ADDRESS_SK
                WHERE C.C_CUSTOMER_SK = ?
                """;

        List<CustomerDto> customers = jdbcTemplate.query(sql, customerRowMapper, id);

        if (customers.isEmpty()) {
            throw new RuntimeException("Customer not found with id: " + id);
        }

        return customers.getFirst();
    }
}