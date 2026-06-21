# customer-api


## Snowflake connection

The service uses Snowflake JDBC.

The following environment variables are required in the .env file:  
```env
SNOWFLAKE_ID
SNOWFLAKE_USERNAME
SNOWFLAKE_PASSWORD
```

Example `application.properties`:

```properties
spring.datasource.driver-class-name=net.snowflake.client.jdbc.SnowflakeDriver
spring.datasource.url=jdbc:snowflake://${SNOWFLAKE_ID}.snowflakecomputing.com/?warehouse=COMPUTE_WH&db=SNOWFLAKE_SAMPLE_DATA&schema=TPCDS_SF10TCL&JDBC_QUERY_RESULT_FORMAT=JSON
spring.datasource.username=${SNOWFLAKE_USERNAME}
spring.datasource.password=${SNOWFLAKE_PASSWORD}
```

## MongoDB connection

MongoDB is used to read the records inserted by the consumer.

```properties
spring.data.mongodb.uri=mongodb://admin:admin123@localhost:27017/customer_pipeline?authSource=admin
```

## Kafka configuration

```properties
spring.kafka.bootstrap-servers=localhost:9092
app.kafka.topic=customer-topic
```

## Start the service

From the `customer-api` folder:

```bash
mvn spring-boot:run
```

Service URL:

```text
http://localhost:8080
```


