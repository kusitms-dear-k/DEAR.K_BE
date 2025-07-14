FROM openjdk:17-jdk
COPY build/libs/be-0.0.1-SNAPSHOT.jar app.jar

ENV TZ Asia/Seoul
ARG ENV
ARG SPRING_PROFILES_ACTIVE

ENTRYPOINT ["java", "-Xms512m", "-jar","-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-Dserver.env=${ENV}", "/app.jar"]