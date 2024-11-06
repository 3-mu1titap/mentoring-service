
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/gateway-0.0.1-SNAPSHOT.jar gateway-service.jar

EXPOSE 9200

ENTRYPOINT ["java", "-jar", "mentoring-service.jar"]

ENV TZ=Asia/Seoul