# Appointment Branch Service
- This service is responsible for managing branch service.
- This service is acting a lookup for the following items:
  - Setting up branches
  - Reading preloaded:
    - Provinces - Province where branch is at.
    - Cities - City under the province where the branch is at.
    - Slot - Slot you can make an appointment on. E.g
      - 08:00 - 09:00
      - 09:00 - 10:00
      - ..............
- Services that require data from this service must be clients that make requests using [gRPC](https://grpc.io/) protocol.

## Existing public Docker Image
- There is an already existing public image you can use without building the new one if you not making code changes:
  - Image - ```docker.io/menelismthembu12/appointment-branch-service```
  - Tag - ```1.0.0```
- The service allows config to be externalized using config-server.

Config
```yaml
infrastructure:
  DB:
    USERNAME: {db_user}
    PASSWORD: {db_password}
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/{branch_db}
    username: ${infrastructure.DB.USERNAME}
    password: ${infrastructure.DB.PASSWORD}
  jpa:
    hibernate:
      ddl-auto: update
    database-platform: org.hibernate.dialect.PostgreSQLDialect
  security:
    oauth2:
      resourceserver:
        jwt:
          jwk-set-uri: http://auth-server:9000/oauth2/jwks
springdoc:
  api-docs:
    path: /branch-service/v3/api-docs
  swagger-ui:
    path: /branch-service/swagger-ui.html
    operations-sorter: method
    doc-expansion: none
app:
  open-api:
    info:
      title: Appointment Branch Service
      description: Appointment Branch Service
      version: 1.0.0
  white-list:
    - "/branch-service/swagger-ui/**"
    - "/branch-service/v3/api-docs/**"
  admin-routes:
    - "/api/v1/branch/admin/**"
    - "/api/v1/province/admin/**"
    - "/api/v1/city/admin/**"
logging:
  level:
    root: info
# gRPC Server Config
grpc:
  server:
    port: 9091
```