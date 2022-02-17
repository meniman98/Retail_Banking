FROM openjdk:8-jdk-alpine
WORKDIR '/app'
ADD target/customer-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "customer-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080