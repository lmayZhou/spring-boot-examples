FROM java:8
MAINTAINER lmay Zhou <lmay@lmaye.com>
VOLUME /tmp
ADD ./spring-boot-elasticsearch-1.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-Xmx200m", "-jar", "/app/spring-boot-elasticsearch-1.0.1-SNAPSHOT.jar"]

EXPOSE 60
