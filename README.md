# Customer Data Pipeline

## Overview

This assessment implements a data pipeline using Java 21 and Spring Boot 3.

The system retrieves customer data from Snowflake, sends selected customers to Kafka, consumes those messages, and stores them in MongoDB.

## Architecture

```text
Snowflake → customer-api → Kafka → kafka-consumer → MongoDB
```

## Prerequisites

* Java 21
* Maven
* Docker Desktop
* Snowflake account
* Git

## Start infrastructure

From the root folder:

```bash
docker compose up -d
```

This starts:

* Kafka
* MongoDB

To verify:

```bash
docker compose ps
```

## Run services

Start `customer-api`:

```bash
cd customer-api
mvn spring-boot:run
```

Start `kafka-consumer`:

```bash
cd kafka-consumer
mvn spring-boot:run
```

## Run tests

```bash
cd customer-api
mvn test
```

```bash
cd ../kafka-consumer
mvn test
```

## Main API endpoints

| Method | Endpoint                       | Description                                    |
| ------ | ------------------------------ | ---------------------------------------------- |
| GET    | `/api/customers` | Retrieves paginated customers from Snowflake   |
| GET    | `/api/customers/fetch/{id}`    | Fetches a customer by ID and sends it to Kafka |
| GET    | `/api/customers/mongo`         | Returns customers stored in MongoDB            |

## API DOC
http://localhost:8080/swagger-ui/index.html
