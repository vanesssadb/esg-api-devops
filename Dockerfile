# ---------- BUILD STAGE ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copia pom e baixa dependências em cache
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# Copia o código e empacota
COPY src ./src
RUN mvn -q -DskipTests clean package

# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# (Opcional) timezone
ENV TZ=America/Maceio

# Copia o jar do stage anterior
COPY --from=build /app/target/*.jar /app/app.jar

# Configs padrões (podem ser sobrepostos por variáveis do compose)
ENV SERVER_PORT=8080
EXPOSE 8080

# Dicas: sobreponha as credenciais via variáveis de ambiente
# SPRING_DATASOURCE_URL=jdbc:oracle:thin:@//oracle:1521/XEPDB1
# SPRING_DATASOURCE_USERNAME=APP
# SPRING_DATASOURCE_PASSWORD=app
# security.jwt.secret=uma-chave-secreta-bem-grande-32+chars
# security.jwt.expiration=86400000

ENTRYPOINT ["java","-jar","/app/app.jar"]
