FROM openjdk:17-jdk

COPY build/libs/be-0.0.1-SNAPSHOT.jar app.jar

COPY src/main/resources/secret/ src/main/resources/secret/

ENTRYPOINT ["java", "-jar", "/app.jar"]
