# Use OpenJDK 17 slim image
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy Maven wrapper and pom.xml first to leverage Docker cache
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copy source code
COPY src src

# Make mvnw executable
RUN chmod +x mvnw

# Build the Spring Boot app
RUN ./mvnw clean package -DskipTests

# Expose port 8080
EXPOSE 8080

# Run the packaged JAR
CMD ["java", "-jar", "target/mgnrega-0.0.1-SNAPSHOT.jar"]
