FROM openjdk:19
LABEL authors="lucas.henrique.s.go@gmail.com"

VOLUME /tmp
EXPOSE 8080

ARG JAR_FILE=target/ApplicationBackend-0.0.1.jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]