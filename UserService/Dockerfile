# Use OpenJDK 17 as base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the Maven-built jar into the container
COPY target/UserService-0.0.1-SNAPSHOT.jar app.jar

# Expose port (User Service runs on 8081)
EXPOSE 8081

# Run the jar
ENTRYPOINT ["java","-jar","app.jar"]
