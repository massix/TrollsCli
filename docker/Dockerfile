FROM openjdk:8-jre
MAINTAINER Massimo Gengarelli <massimo.gengarelli@gmail.com>

# Set proxies during installation if needed
RUN mkdir -p /etc/apt/apt.conf.d/
RUN touch /etc/apt/apt.conf.d/01proxy
RUN if test "x${HTTP_PROXY}" != "x"; then echo "Acquire::http::proxy \"${HTTP_PROXY}\";" >> /etc/apt/apt.conf.d/01proxy; fi
RUN if test "x${HTTPS_PROXY}" != "x"; then echo "Acquire::https::proxy \"${HTTPS_PROXY}\";" >> /etc/apt/apt.conf.d/01proxy; fi

COPY TrollsController-1.0.0-SNAPSHOT.jar /usr/lib/
COPY entrypoint.sh /
RUN chmod +x /entrypoint.sh
RUN apt-get update
RUN apt-get install -y rlwrap

# Clean installation
RUN rm -f /etc/apt/apt.conf.d/01proxy

ENTRYPOINT ["/entrypoint.sh"]