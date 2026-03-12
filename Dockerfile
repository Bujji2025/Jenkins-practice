FROM eclipse-temurin:11-jre-jammy
WORKDIR /app
COPY target/jenkins-practice-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]