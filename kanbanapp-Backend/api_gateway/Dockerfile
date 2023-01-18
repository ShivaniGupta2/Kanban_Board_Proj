FROM openjdk:11-jre-slim
WORKDIR usr/src
EXPOSE 9001
ADD ./target/api_gateway-0.0.1-SNAPSHOT.jar /usr/src/api_gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","api_gateway-0.0.1-SNAPSHOT.jar"]

