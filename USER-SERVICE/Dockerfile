FROM openjdk:11

EXPOSE 8081

ADD target/*.jar USER-SERVICE-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/USER-SERVICE-0.0.1-SNAPSHOT.jar"]