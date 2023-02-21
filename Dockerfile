FROM openjdk:11-jdk

COPY target/claims-0.0.1-SNAPSHOT.jar /app/api.jar
COPY src/main/resources/application.properties /app/config/application.properties

EXPOSE 4000

ENTRYPOINT ["java", "-jar", "-Dspring.config.import=optional:configserver:http://20.62.171.46:8888", "-Dspring.config.name=application", "-Dspring.config.location=file:/app/config/", "/app/api.jar"]                                                                                                     
