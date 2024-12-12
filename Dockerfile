FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/mentoring-0.0.1-SNAPSHOT.jar mentoring-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "mentoring-service.jar"]

ENV TZ=Asia/Seoul
