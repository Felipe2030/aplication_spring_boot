# Etapa de construção
FROM ubuntu:latest AS builder

RUN apt-get update && apt-get install -y openjdk-17-jdk maven

# Copia os arquivos do projeto para o contêiner
WORKDIR /app
COPY . .

# Constrói o projeto com o Maven Wrapper
RUN ./mvnw clean install

# Etapa de execução
FROM openjdk:17-jdk-slim

EXPOSE 8080

# Copia o JAR construído na etapa anterior para o contêiner
COPY --from=builder /app/target/deploy_render-1.0.0.jar /app.jar

# Comando de execução
ENTRYPOINT ["java", "-jar", "/app.jar"]
