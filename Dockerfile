FROM gradle:8.3-jdk-17-alpine as builder
USER root
WORKDIR /builder
ADD . /builder
RUN gradle build --stacktrace

FROM openjdk:17-jre-alpine
WORKDIR /app
EXPOSE 8080
COPY --from=builder /builder/build/libs/springtemplate-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "springtemplate-0.0.1-SNAPSHOT.jar"]