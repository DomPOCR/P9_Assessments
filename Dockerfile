FROM openjdk:8-jdk-alpine
EXPOSE 8082
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} assessment.jar
ENTRYPOINT ["java","-jar","/assessment.jar"]