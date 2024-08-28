FROM openjdk:11.0.8-jre-slim

WORKDIR /app

COPY ./target/rest_service-0.0.1-SNAPSHOT.jar /app

EXPOSE 80

CMD ["java", "-jar", "rest_service-0.0.1-SNAPSHOT.jar"]


