FROM maven:latest AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /app/src/
RUN mvn package

FROM openjdk:17-oracle

WORKDIR /app

COPY --from=builder /app/target/demo-1.0.jar .

EXPOSE 8080

CMD ["java", "-jar", "demo-1.0.jar"]