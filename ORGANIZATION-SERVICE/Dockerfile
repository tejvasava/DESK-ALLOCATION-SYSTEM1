FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} USER-SERVICE.jar

ENTRYPOINT ["java" ,"-jar", "/USER-SERVICE.jar" ]

EXPOSE 8082