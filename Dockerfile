FROM amazoncorretto:25-alpine3.19
LABEL authors="Raposio"

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]