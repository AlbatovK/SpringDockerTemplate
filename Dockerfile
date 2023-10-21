FROM gradle:8-jdk17 as builder
USER root
WORKDIR /builder
ADD . /builder
RUN gradle build --stacktrace

FROM openjdk:17-jdk-slim
WORKDIR /app
EXPOSE 8080
COPY --from=builder /builder/build/libs/springtemplate-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "springtemplate-0.0.1-SNAPSHOT.jar"]