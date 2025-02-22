FROM maven:3.9.9 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21
COPY --from=build /target/Automatic-Invoice-Generator-0.0.1-SNAPSHOT.jar Automatic-Invoice-Generator.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","Automatic-Invoice-Generator.jar"]