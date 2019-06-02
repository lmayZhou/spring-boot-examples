FROM java:8
MAINTAINER lmay Zhou <lmay@lmaye.com>
VOLUME /tmp
ADD ./target/spring-boot-docker-compose-1.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-Xmx200m", "-jar", "/app/spring-boot-docker-compose-1.0.1-SNAPSHOT.jar"]

EXPOSE 82
