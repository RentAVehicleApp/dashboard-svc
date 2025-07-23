FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app
COPY . /app

# Собираем весь мультимодульный проект
RUN mvn clean package -DskipTests

# Второй stage: только рантайм
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Копируем итоговый JAR (замени путь и имя JAR при необходимости)
COPY --from=builder /app/dashboard-service-api/target/dashboard-service-api-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]