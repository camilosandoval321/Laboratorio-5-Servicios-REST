# ============================================================
# ETAPA 1: BUILD
# Usa una imagen con Maven + Java 17 para compilar el proyecto
# ============================================================
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia primero el pom.xml para aprovechar el caché de capas de Docker
# (si el pom no cambia, no re-descarga dependencias)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia el código fuente y compila
COPY src ./src
RUN mvn clean package -DskipTests

# ============================================================
# ETAPA 2: RUN
# Imagen más liviana, solo necesita Java para ejecutar el .jar
# ============================================================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copia el .jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# El archivo SQLite se guardará en /app/data dentro del contenedor
# (montamos ese directorio como volumen para que los datos persistan)
VOLUME /app/data

# Variable de entorno para que SQLite guarde el archivo en /app/data
ENV SPRING_DATASOURCE_URL=jdbc:sqlite:/app/data/competitors.db

# Puerto que expone la aplicación
EXPOSE 8080

# Comando para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
