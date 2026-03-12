FROM maven:3.8-openjdk-11-slim AS builder
WORKDIR /build
COPY pom.xml .
COPY src/ src/
RUN mvn clean package -DskipTests


FROM eclipse-temurin:11-jre-jammy
WORKDIR /app
COPY --from=builder /build/target/jenkins-practice-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]