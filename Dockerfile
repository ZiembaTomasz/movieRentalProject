FROM gradle:6.1.1-jdk8 AS builder
ENV BUILD /build
RUN mkdir $BUILD
COPY src $BUILD/src
COPY build.gradle $BUILD
COPY settings.gradle $BUILD
WORKDIR $BUILD
RUN gradle build --no-daemon


FROM openjdk:8-jdk-alpine
ENV APP /app
RUN mkdir $APP
WORKDIR $APP
COPY --from=builder /build/build/libs/rental-1.0.jar .
ENTRYPOINT ["java", "-jar", "rental-1.0.jar"]
EXPOSE 8080