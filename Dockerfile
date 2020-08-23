FROM openjdk:8-jre-alpine

WORKDIR /app

ADD http://host.docker.internal:8086/artifactory/before-exam/com/example/demo/0.0.1-SNAPSHOT/demo-0.0.1-SNAPSHOT.jar demo-0.0.1.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "demo-0.0.1.jar"]