# Aparentemente cuando empaquetas con nixpacks en Railway, nixpacks sobreescribe
# el puerto que el contenedor de Railway pone por defecto por el que pongas en application.properties, 
# pero cuando despliegas una imagen propia como esta no lo hace.
# Acuerdate de cambiar el puerto en el "contenedor" de railway

FROM maven:4.0.0-rc-5-eclipse-temurin-21-noble AS build
WORKDIR /app
COPY . .
RUN mvn clean package -D skipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copiamos el jar compilado del stage anterior
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]