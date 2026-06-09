# SpringUnitTesting

Frozen FIB-UPC EPCSD coursework: Spring Boot 2.6.2 microservices on Java 11, demonstrating unit, integration, and architecture testing patterns. Treat as an archive — do not modernize dependencies or restructure modules.

## Architecture

Three independent Maven projects, each with its own `pom.xml`:

- `private-epcsd-spring/epcsd-spring-showcatalog/` — Show/category/performance REST API with Kafka event publishing.
- `private-epcsd-spring/epcsd-spring-notification/` — Kafka consumer for show notifications and user management.
- `epcsd-spring-2023-productcatalog-solution/` — Product catalog reference solution with the richest test suite (unit + integration + ArchUnit).

Shared infra (PostgreSQL, Kafka, Adminer) is provided via `docker-compose.yml` files in `epcsd-spring-2023/` and `private-epcsd-spring/`.

## Build and Test

Per service, from that service's directory:

- `mvn test` — run tests for one service.
- `mvn spring-boot:run` — run the service (requires docker-compose infra up).
- `docker-compose up -d` (from the parent folder) — bring up Postgres + Kafka.

## Conventions

Tests in `src/test/java` are grouped by intent in the productcatalog solution:

- Unit tests: `*UnitTest` (e.g. `CategoryControllerUnitTest`, `ProductCatalogServiceUnitTest`) — mock collaborators.
- Integration tests: `*IntegrationTest` (e.g. `CatalogRepositoryIntegrationTest`) — JPA against a test DB.
- Architecture tests: `*ArchitectureTest` (e.g. `ProductCatalogArchitectureTest`) — ArchUnit rules enforcing layer boundaries.

## Pitfalls

- Pinned to Java 11 and Spring Boot 2.6.2; newer JDKs (17+) or Boot 3.x will break the build. Use a JDK 11 toolchain.
- No root aggregator POM — each project must be built from its own directory.
- Integration tests and `spring-boot:run` need the docker-compose stack running first.

See [README.md](README.md).
