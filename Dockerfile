# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
<<<<<<< HEAD
EXPOSE 8080
=======
EXPOSE 8080
>>>>>>> bc002d72aed9aeae15c9caeb8b29ea96b5498e3f
