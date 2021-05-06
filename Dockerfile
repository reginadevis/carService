FROM openjdk:8-jdk-alpine
MAINTAINER regina
ARG JAR_FILE=target/car-1.0.jar
COPY ${JAR_FILE} car-1.0.jar
ENTRYPOINT ["java","-jar","/car-1.0.jar"]
