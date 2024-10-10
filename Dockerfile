FROM openjdk:17-alpine

EXPOSE 8080

USER root

# Create a group and user
RUN addgroup -S mb-user && adduser -S mb-user -G mb-user

USER mb-user

WORKDIR /

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
