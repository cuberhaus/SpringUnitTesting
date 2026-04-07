# Security Policy — SpringUnitTesting

## Reporting a Vulnerability
If you discover a security vulnerability, please email polcg10@gmail.com. Do not open a public issue.

## Security Considerations

### API Security
- REST endpoints should be secured with Spring Security. Add authentication and authorization before exposing beyond `localhost`.
- OpenAPI/Swagger docs expose your API surface — disable or restrict access in production (`springdoc.swagger-ui.enabled=false`).
- Validate all request bodies using Bean Validation (`@Valid`, `@NotNull`, `@Size`, etc.).
- Return generic error messages — do not expose stack traces or internal details via error responses.

### Database Credentials
- PostgreSQL connection credentials (`spring.datasource.url`, `username`, `password`) must be stored in environment variables or Spring profiles, not in `application.properties` committed to source control.
- Use a dedicated database user with minimal privileges (no `SUPERUSER`, no `CREATE DATABASE`).
- Enable SSL for database connections in production.

### Kafka Topic Access
- Kafka topics may contain sensitive business data. Restrict access using Kafka ACLs.
- Use SASL/SSL for Kafka broker connections in production — plaintext connections expose message content.
- Validate and sanitize message payloads — treat incoming Kafka messages as untrusted input.
- Monitor consumer lag and dead letter topics for signs of issues.

### Spring Boot Hardening
- Disable Actuator endpoints in production or restrict them to management ports with authentication.
- Set `server.error.include-stacktrace=never` in production.
- Use `spring.datasource.hikari.leak-detection-threshold` to detect connection leaks.

### Recommendations
- Run `mvn dependency-check:check` to scan for known CVEs in dependencies.
- Keep Spring Boot, Spring Security, and all starters updated.
- Use profiles (`application-prod.properties`) to separate dev/prod configurations.
- Implement request rate limiting on public-facing endpoints.
