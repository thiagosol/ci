FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /build

copy pom.xml .
copy src src

RUN mvn package --batch-mode

FROM openjdk:17-alpine AS release

COPY --from=build /build/target/*.jar /app.jar
COPY run.sh /
RUN chmod +x /run.sh
ENTRYPOINT ["/run.sh"]