FROM openjdk:24
LABEL maintainer = devtiro.com
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]