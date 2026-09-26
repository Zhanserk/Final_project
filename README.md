# Final Project — Spring Boot REST API

REST API for a product catalog: items, categories and countries of origin, with user registration and role-based access.

## Tech stack

- **Java 21**, **Spring Boot 3.5** (Web, Data JPA, Security, AOP)
- **PostgreSQL 16** with **Flyway** migrations
- **MapStruct** DTO mapping, **Lombok**
- **JUnit 5** tests for services and mappers
- **Docker** + Docker Compose, Gradle

## Features

- CRUD for items, categories and countries (`GET / POST / PUT / DELETE`)
- Layered architecture: controller → service (interface + implementation) → repository
- Entities are exposed only through DTOs (MapStruct mappers)
- User registration, passwords hashed with **BCrypt**
- Role-based access with `@PreAuthorize` — deleting items and listing them via `/user/items` requires `ROLE_ADMIN`
- Database schema and seed data managed by Flyway (`V1__init.sql`, `V2__Insert.sql`)

## API

| Method | Path | Description |
|---|---|---|
| GET / POST | `/item` | List items / create an item |
| GET / PUT / DELETE | `/item/{id}` | Get / update / delete an item (delete — admin only) |
| GET / POST | `/item/category` | List / create categories |
| GET / PUT / DELETE | `/item/category/{id}` | Get / update / delete a category |
| GET / POST | `/item/country` | List / create countries |
| GET / PUT / DELETE | `/item/country/{id}` | Get / update / delete a country |
| POST | `/user/register` | Register a user |
| GET | `/user/items` | Items for administrators (HTTP Basic, `ROLE_ADMIN`) |

## Run

**With Docker** (PostgreSQL + the app):

```bash
docker compose -f DockerCompose.yaml up --build
```

The API is available at http://localhost:8081.

**Locally** (needs PostgreSQL on `localhost:5432`, database `final_new_db`):

```bash
./gradlew bootRun
```

**Tests:**

```bash
./gradlew test
```
