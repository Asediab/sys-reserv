FROM adoptopenjdk/openjdk14:alpine-jre

EXPOSE 9092

ARG JAR_FILE=target/*.jar

WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]