# FROM maven:3.6.2-jdk-12 AS build
FROM prithivid/flashexdepends:latest AS build
COPY src /app/src
COPY pom.xml /app/pom.xml
RUN mvn -f /app/pom.xml clean package -DskipTests=true

FROM gesellix/wait-for as waitfor

FROM openjdk:12-alpine
COPY --from=build /app/target/*.jar app.jar
COPY --from=waitfor ./wait-for ./wait-for

EXPOSE 6000

ENTRYPOINT [ "./wait-for", "config-server:8888", "-t", "20", "--", "java", "-jar", "app.jar"]
# ENTRYPOINT ["java","-jar","app.jar"]