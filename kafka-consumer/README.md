# kafka-consumer


## Kafka configuration

```properties
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=customer-group
spring.kafka.consumer.auto-offset-reset=earliest
app.kafka.topic=customer-topic
```

The consumer listens to:

```text
customer-topic
```

## MongoDB configuration

```properties
spring.data.mongodb.uri=mongodb://admin:admin123@localhost:27017/customer_pipeline?authSource=admin
```

Database:

```text
customer_pipeline
```

Collection:

```text
customer_data
```

Each consumed Kafka message is inserted as a new MongoDB document.

## Start the service

Make sure Kafka and MongoDB are running:

```bash
docker compose up -d
```

From the `kafka-consumer` folder:

```bash
mvn spring-boot:run
```

Service port:

```text
8082
```

## Verify MongoDB data

Using the API:

```bash
curl "http://localhost:8080/api/customers/mongo"
```

Or using Mongo shell:

```bash
docker exec -it mongo mongosh -u admin -p admin123 --authenticationDatabase admin
```

```javascript
use customer_pipeline
db.customer_data.find().pretty()
```
