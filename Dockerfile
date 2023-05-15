FROM maven:3.8.4-openjdk-17 as build
COPY . /src
WORKDIR /src
RUN mvn clean install && cd target && mv *.jar app.jar
EXPOSE 8080

FROM openjdk:17
COPY --from=build /src/target/app.jar /usr/app.jar
ENTRYPOINT ["java","-jar","/usr/app.jar"]

