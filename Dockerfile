FROM openjdk:8-jre-alpine
MAINTAINER ming.lu
USER root
RUN mkdir -p /home/SpiderWiki
WORKDIR /home/SpiderWiki
COPY target/SpiderWiki-1.0-SNAPSHOT.jar SpiderWiki-1.0-SNAPSHOT.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar", "SpiderWiki-1.0-SNAPSHOT.jar"]
