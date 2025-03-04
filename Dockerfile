# Use an OpenJDK image to build your app with Maven installed
FROM maven:3.8.1-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy your pom.xml and download dependencies
COPY pom.xml .

# Download dependencies (faster than copying the entire project)
RUN mvn dependency:go-offline

# Copy the rest of your application code
COPY src /app/src

# Package the application (create the jar file)
RUN mvn clean package

# Now, create a smaller container to run your app
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR from the previous image
COPY --from=build /app/target/Your1PropertyConnect-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app is running on (default Spring Boot port is 8080)
EXPOSE 8080

# Command to run your Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
