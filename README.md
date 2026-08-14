# Project Moto Trip Dates 🏍️💨💥 - Java

An international, SaaS service and high-performance modular monolith platform designed for motorcycle travelers, community building 
(Brotherhoods), and ride-sharing/dating. The system supports real-time **GPS tracking**, active location matching, and 
automated SOS alerts.

The application is built in **Java** as a Modular **Monolith** using **DDD** (Domain-Driven Design) and **Hexagonal Architecture** 
(Ports and Adapters) principles. This ensures strict isolation of core business logic from framework-specific 
dependencies and enables seamless migration to a microservices architecture in the future.

<p align="left">
  <img src="doc/images/java-moto-trip-dates1.jpeg" width="400" alt="Sol y Pago 1" />
  <img src="doc/images/java-moto-trip-dates6.jpeg" width="400" alt="Sol y Pago 2" />
</p>

## 🛠 Tech Stack & Tools
- **Framework:** Spring Boot
- **Database:** PostgreSQL (High-performance relational storage)
- **Database Migrations:** Flyway
- **Cloud Storage:** AWS S3 (Async clients)
- **Local Infrastructure:** Docker (AWS S3 emulation)

## 🧪 Prerequisites & SetupJDK 17

- JDK 17
- Docker (for PostgreSQL with PostGIS extension)

## 🐳 How to Run the Project (Docker & Local Deployment)

The platform requires **PostgreSQL/PostGIS**, **Apache Kafka** (for SOS alerts), and **MinIO** (S3-compatible storage for Tinder-style photos).

### 🚀 Running the Full Stack (Including Java Backend)

1. **Build and start** all services (including compiling the Java app inside Docker):
    ```bash
    docker-compose up -d --build
    ```

2. **Follow logs** of the running backend application:
    ```bash
    docker-compose logs -f app
    ```

3. **Stop the entire infrastructure**:
    ```bash
    docker-compose down
    ```

### 💻 Running Backend Locally (For Hot-Reload in IDE)

If you prefer to run and debug the Java application directly within **IntelliJ IDEA**, you only need to start the backing infrastructure:

1. **Start only dependencies** (DB, Kafka, MinIO):
    ```bash
    docker-compose up -d motodb kafka minio
    ```

2. Run the `JavaProjectMotoTripDatesApplication` from your IDE.

3. **Access Infrastructure Web Consoles:**
    * **MinIO Storage Browser:** [http://localhost:9001](http://localhost:9001) (User: `minio_admin`, Pass: `minio_secure_password`)

