FROM openjdk:11
ARG JAR_FILE=./build/libs/fgmate-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY src/main/resources/application.yml /config/application.yml
ENTRYPOINT ["java","-jar","/app.jar","--spring.config.location=/config/"]