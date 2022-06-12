FROM openjdk:15
ARG APP_JAR=build/libs/*.jar
COPY ${APP_JAR} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]