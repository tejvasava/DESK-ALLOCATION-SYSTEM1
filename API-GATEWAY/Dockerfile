FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} API-GATEWAY.jar

ENTRYPOINT ["java" ,"-jar", "/API-GATEWAY.jar" ]

EXPOSE 9090