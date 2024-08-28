FROM openjdk:8-jdk-alpine
ADD target/user-mysql.jar user-mysql.jar
ADD src/main/resources/initialData.csv initialData/initialData.csv
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "user-mysql.jar"]