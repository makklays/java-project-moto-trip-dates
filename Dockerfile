# Этап 1: Сборка проекта с помощью Maven
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
# Кэшируем зависимости
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Этап 2: Запуск готового jar-файла
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/java-project-moto-trip-dates-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

