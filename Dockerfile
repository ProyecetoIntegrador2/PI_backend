# Use the official OpenJDK 17 image as a base
FROM openjdk:17-jdk-slim AS build

# Set the working directory
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml and the source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Use a smaller image for the runtime
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port (change if necessary)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]