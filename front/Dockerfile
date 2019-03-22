FROM adoptopenjdk/openjdk11 as builder

WORKDIR /app
ADD . .
RUN ./gradlew bootJar --no-daemon

FROM adoptopenjdk/openjdk11

COPY --from=builder /app/build/libs/front-0.0.1-SNAPSHOT.jar .

#COPY build/libs/front-0.0.1-SNAPSHOT.jar . #in local
CMD ["java", "-jar", "front-0.0.1-SNAPSHOT.jar"]