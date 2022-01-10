FROM openjdk:11
MAINTAINER jd_0005@hotmail.com
ADD target/payment-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar", "-web -webAllowOthers -tcp -tcpAllowOthers -browser"]
EXPOSE 8080