FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} CLOUD-CONFIG.jar

ENTRYPOINT ["java" ,"-jar", "/CLOUD-CONFIG.jar" ]

EXPOSE 9296