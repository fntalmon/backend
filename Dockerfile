# Usa una imagen de Maven con OpenJDK 17 para construir el proyecto
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Usa una imagen de Amazon Corretto 17 para ejecutar la aplicación
FROM amazoncorretto:17
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]
