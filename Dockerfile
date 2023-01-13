FROM openjdk:11
VOLUME /tmp
ADD target/claims-0.0.1-SNAPSHOT.jar claims-docker.jar
ENTRYPOINT ["java", "-jar", "claims-docker.jar"]