FROM openjdk:8-jdk-alpine 
WORKDIR '/app'
ADD target/rules-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "rules-0.0.1-SNAPSHOT.jar"]
EXPOSE 8083