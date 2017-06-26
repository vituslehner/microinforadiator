FROM resin/raspberry-pi-alpine-openjdk:openjdk-8u121-jdk
MAINTAINER Vitus Lehner <student@vitus-lehner.de>

RUN apk add --update \
    python \
    python-dev \
    py-pip \
    build-base \
  && pip install virtualenv \
  && easy_install Pillow \
  && rm -rf /var/cache/apk/*

RUN pip install -v sense-hat

ADD . /opt

WORKDIR /opt/ulp-mir

CMD modprobe i2c-dev && python ../sense-hat/test.py
#java -jar bin/ulp-mikroinfostrahler-$(cat version.txt).jar
#["java", "-jar", "/opt/ulp-mir/starter.jar"]