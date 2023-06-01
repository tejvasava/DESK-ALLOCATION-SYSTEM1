FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} FLOOR-SERVICE.jar

ENTRYPOINT ["java" ,"-jar", "/FLOOR-SERVICE.jar" ]

EXPOSE 8083