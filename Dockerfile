FROM openjdk:17-jdk-slim AS build

# Instala Maven
RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY . /app

RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/Backend-Wakanda-Residuos-0.0.1-SNAPSHOT.jar /app/backend_wakanda_residuos.jar

# Descargar wait-for-it.sh
RUN apt-get update && apt-get install -y curl && \
    curl -o /app/wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh && \
    chmod +x /app/wait-for-it.sh

EXPOSE 8096

ENV EUREKA_SERVER_URL=http://eureka-server:8761/eureka/

ENTRYPOINT ["./wait-for-it.sh", "eureka-server:8761", "--", "java", "-jar", "/app/backend_wakanda_residuos.jar"]
