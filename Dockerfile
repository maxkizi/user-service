FROM openjdk:11-jdk
ARG USER_SERVICE_JAR=target/*.jar
COPY ${USER_SERVICE_JAR}  userservice.jar
ENTRYPOINT ["java", "-jar", "/userservice.jar"]