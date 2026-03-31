# ======================
# STAGE 1: Build (Maven)
# ======================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# copy pom.xml dulu (biar dependency di-cache)
COPY pom.xml .
# download dependency 
RUN mvn dependency:go-offline
# copy source code
COPY src ./src
# build jar
RUN mvn clean package -DskipTests

# ======================
# STAGE 2: Run
# ======================
FROM eclipse-temurin:17-jdk
WORKDIR /app
# copy hasil build dari stage sebelumnya
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","app.jar"]