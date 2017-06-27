FROM resin/raspberry-pi-alpine-openjdk:openjdk-8u121-jdk
MAINTAINER Vitus Lehner <student@vitus-lehner.de>

RUN apk add --update \
    python \
    python-dev \
    py-pip \
    build-base \
    zlib \
    zlib-dev \
    jpeg-dev \
  && pip install -U pip setuptools virtualenv \
  && easy_install Pillow \
  && rm -rf /var/cache/apk/*

ENV LIBRARY_PATH=/lib:/usr/lib

RUN pip install -v sense-hat

ADD . /opt

WORKDIR /opt/ulp-mir

CMD echo "Hallo" && modprobe i2c-dev && echo $(python ../sense-hat/test.py)
#java -jar bin/ulp-mikroinfostrahler-$(cat version.txt).jar
#["java", "-jar", "/opt/ulp-mir/starter.jar"]