FROM openjdk:11-jdk-alpine
WORKDIR '/app'
ADD target/transaction-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "transaction-0.0.1-SNAPSHOT.jar"]
EXPOSE 8082