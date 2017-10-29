FROM openjdk:8-jre
MAINTAINER Massimo Gengarelli <massimo.gengarelli@gmail.com>

COPY TrollsController-1.0.0-SNAPSHOT.jar /usr/lib/
COPY entrypoint.sh /
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]