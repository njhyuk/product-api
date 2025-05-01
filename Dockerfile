FROM gradle:8.5-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle clean build -x test --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
COPY entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh
RUN apt-get update && apt-get install -y curl
EXPOSE 8080
ENTRYPOINT ["/app/entrypoint.sh"]
