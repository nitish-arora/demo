FROM openjdk:8-jre-alpine

WORKDIR /app

RUN wget -O target/demo-0.0.1.jar http://artifactory_nagp:8086/artifactory/nagp-practice/com/example/demo/0.0.1-SNAPSHOT/demo-0.0.1-20200808.142724-2.jar

COPY target/demo-0.0.1.jar /app/

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "demo-0.0.1.jar"]