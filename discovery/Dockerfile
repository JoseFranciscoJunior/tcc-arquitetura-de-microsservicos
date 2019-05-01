FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/discovery.jar app.jar
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-jar", "/app.jar"]