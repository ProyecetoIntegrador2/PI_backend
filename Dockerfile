# syntax=docker/dockerfile:1.4

### --- Build Stage ---
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copy Maven wrapper and configs
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Pre-download dependencies with caching
RUN --mount=type=cache,target=/root/.m2 ./mvnw -B dependency:go-offline dependency:resolve-plugins dependency:resolve

# Make mvnw executable
RUN chmod +x mvnw

# Now copy the source code
COPY src ./src

# Build the application
RUN --mount=type=cache,target=/root/.m2 ./mvnw -B clean package -DskipTests

### --- Runtime Stage ---
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy only the built jar
COPY --from=build /app/target/*.jar app.jar

# Expose app port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]