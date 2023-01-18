#here openjdk is docker image for Java 11, as in pom.xml Java version is 11
FROM openjdk:11-jre-slim
#creating a working directory inside the image
WORKDIR usr/lib
EXPOSE 8082
#setting environment variable same name that are there in application.properties file

#Copy executable jar file getting created inside target and add it in usr/lib working director
ADD ./target/kanbanRegisterService-0.0.1-SNAPSHOT.jar /usr/lib/kanbanRegisterService-0.0.1-SNAPSHOT.jar
#Run the jar file
ENTRYPOINT ["java","-jar","kanbanRegisterService-0.0.1-SNAPSHOT.jar"]



