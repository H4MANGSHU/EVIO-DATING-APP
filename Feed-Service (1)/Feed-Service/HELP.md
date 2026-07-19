# Read Me First
The following was discovered as part of building this project:

* The original package name 'Feed-Service.' is invalid and this project uses 'Feed_Service' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.1.0/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.1.0/maven-plugin/build-image.html)
* [Spring Boot Testcontainers support](https://docs.spring.io/spring-boot/4.1.0/reference/testing/testcontainers.html#testing.testcontainers)
* [Testcontainers RabbitMQ Module Reference Guide](https://java.testcontainers.org/modules/rabbitmq/)
* [Testcontainers Elasticsearch Container Reference Guide](https://java.testcontainers.org/modules/elasticsearch/)
* [Testcontainers Kafka Modules Reference Guide](https://java.testcontainers.org/modules/kafka/)
* [Spring Web](https://docs.spring.io/spring-boot/4.1.0/reference/web/servlet.html)
* [Spring Data Elasticsearch](https://docs.spring.io/spring-boot/4.1.0/reference/data/nosql.html#data.nosql.elasticsearch)
* [Elasticsearch](https://docs.spring.io/spring-boot/4.1.0/reference/data/nosql.html#data.nosql.elasticsearch)
* [Testcontainers](https://java.testcontainers.org/)
* [Spring for Apache Kafka](https://docs.spring.io/spring-boot/4.1.0/reference/messaging/kafka.html)
* [Apache Kafka Streams Support](https://docs.spring.io/spring-kafka/reference/streams.html)
* [Apache Kafka Streams Binding Capabilities of Spring Cloud Stream](https://docs.spring.io/spring-cloud-stream/reference/kafka/kafka-streams-binder/usage.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/4.1.0/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Docker Compose Support](https://docs.spring.io/spring-boot/4.1.0/reference/features/dev-services.html#features.dev-services.docker-compose)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/4.1.0/reference/using/devtools.html)
* [Spring Modulith](https://docs.spring.io/spring-modulith/reference/)
* [Spring Data Redis (Access+Driver)](https://docs.spring.io/spring-boot/4.1.0/reference/data/nosql.html#data.nosql.redis)
* [Spring for RabbitMQ](https://docs.spring.io/spring-boot/4.1.0/reference/messaging/amqp.html)
* [Eureka Discovery Client](https://docs.spring.io/spring-cloud-netflix/reference/spring-cloud-netflix.html#_service_discovery_eureka_clients)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Samples for using Apache Kafka Streams with Spring Cloud stream](https://github.com/spring-cloud/spring-cloud-stream-samples/tree/main/kafka-streams-samples)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)
* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)
* [Service Registration and Discovery with Eureka and Spring Cloud](https://spring.io/guides/gs/service-registration-and-discovery/)

### Docker Compose support
This project contains a Docker Compose file named `compose.yaml`.
In this file, the following services have been defined:

* elasticsearch: [`docker.elastic.co/elasticsearch/elasticsearch:9.3.3`](https://www.docker.elastic.co/r/elasticsearch)
* rabbitmq: [`rabbitmq:latest`](https://hub.docker.com/_/rabbitmq)
* redis: [`redis:latest`](https://hub.docker.com/_/redis)

Please review the tags of the used images and set them to the same as you're running in production.

### Testcontainers support

This project uses [Testcontainers at development time](https://docs.spring.io/spring-boot/4.1.0/reference/features/dev-services.html#features.dev-services.testcontainers).

Testcontainers has been configured to use the following Docker images:

* [`docker.elastic.co/elasticsearch/elasticsearch:9.3.3`](https://www.docker.elastic.co/r/elasticsearch)
* [`apache/kafka-native:latest`](https://hub.docker.com/r/apache/kafka-native)
* [`rabbitmq:latest`](https://hub.docker.com/_/rabbitmq)
* [`redis:latest`](https://hub.docker.com/_/redis)

Please review the tags of the used images and set them to the same as you're running in production.

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

