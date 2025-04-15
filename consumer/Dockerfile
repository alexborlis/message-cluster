FROM eclipse-temurin:24-jdk-alpine
WORKDIR /app
COPY build/libs/consumer.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]