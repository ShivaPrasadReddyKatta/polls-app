FROM openjdk:latest
ADD target/polls-app-backend.jar polls-app-backend.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "polls-app-backend.jar"]