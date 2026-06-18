# Use an official lightweight Java 21 image as the base environment
FROM eclipse-temurin:21-jre-alpine

# Create a directory inside the container for our app
WORKDIR /app

# Copy the compiled .jar file from your target folder into the Docker container
# (Make sure the name matches what Maven actually built in your target folder!)
COPY target/mission5-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 so DigitalOcean knows where to send web traffic
EXPOSE 8080

# The command to start the application when the container launches
ENTRYPOINT ["java", "-jar", "app.jar"]