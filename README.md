# Spring Unit Testing

EPCSD course projects demonstrating Spring Boot application development with unit testing, integration testing, and architecture testing.

## Overview

Contains several Spring Boot microservices and their test suites:

- **Show Catalog** – REST API for managing shows, categories, and performances with Kafka event publishing
- **Notification Service** – Kafka consumer for show notifications and user management
- **Product Catalog** – Product/Category/Item management with comprehensive test coverage including ArchUnit architecture tests

## Structure

```
├── private-epcsd-spring/
│   ├── showcatalog/          # Show catalog service
│   └── notification/         # Notification service
└── epcsd-spring-2023-productcatalog-solution/
    └── src/
        ├── main/             # Product catalog service
        └── test/             # Unit, integration & architecture tests
```

## Testing

The product catalog project includes:

- **Unit tests** – Controller and service layer tests (`CategoryControllerUnitTest`, `ProductCatalogServiceUnitTest`)
- **Integration tests** – JPA repository tests (`CatalogRepositoryIntegrationTest`)
- **Architecture tests** – ArchUnit rules (`ProductCatalogArchitectureTest`)

## Tech Stack

- **Java 11** with Spring Boot 2.6.2
- **Spring Data JPA** + PostgreSQL
- **Apache Kafka** for event-driven messaging
- **SpringDoc OpenAPI** for API documentation
- **JUnit 5** + ArchUnit for testing
- **Maven** for build management
