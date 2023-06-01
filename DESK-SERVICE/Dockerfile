FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} DESK-SERVICE.jar

ENTRYPOINT ["java" ,"-jar", "/DESK-SERVICE.jar" ]

EXPOSE 8084