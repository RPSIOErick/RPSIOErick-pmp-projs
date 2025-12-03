FROM amazoncorretto:25-alpine3.19
LABEL authors="Raposio"

WORKDIR /app

EXPOSE 8080

ADD https://dtdg.co/latest-java-tracer /app/dd-java-agent.jar

ARG JAR_FILE=pmp-gateway/target/*.jar
COPY ${JAR_FILE} /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]