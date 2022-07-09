FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
ENV TZ America/El_Salvador
COPY ${JAR_FILE} app.jar 
ENTRYPOINT ["java","-jar","/app.jar"]