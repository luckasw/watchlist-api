FROM maven:3.9-eclipse-temurin-21-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM maven:3.9-eclipse-temurin-21-alpine
WORKDIR /app
COPY --from=builder /app/target/watchlist-api-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]