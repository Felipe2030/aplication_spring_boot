# Etapa de construção
FROM ubuntu:latest AS builder

RUN apt-get update && apt-get install -y openjdk-17-jdk maven

# Cria um diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para o contêiner
COPY . .

# Dá permissões de execução ao Maven Wrapper
RUN chmod +x mvnw

# Constrói o projeto com o Maven Wrapper
RUN ./mvnw spring-boot:run

# Etapa de execução
FROM openjdk:17-jdk-slim

EXPOSE 8080

# Copia o JAR construído na etapa anterior para o contêiner
COPY --from=builder /app/target/deploy_render-1.0.0.jar /app.jar

# Comando de execução
ENTRYPOINT ["java", "-jar", "/app.jar"]
