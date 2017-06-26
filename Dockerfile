FROM resin/raspberry-pi-alpine-openjdk:openjdk-8u121-jdk
MAINTAINER Vitus Lehner <student@vitus-lehner.de>

RUN apk add --update \
    python \
    python-dev \
    py-pip \
    build-base \
    curl \
    tar \ 
  && pip install virtualenv \
  && rm -rf /var/cache/apk/*

RUN mkdir /opt/sense-hat
WORKDIR /opt/sense-hat
RUN curl https://github.com/RPi-Distro/python-sense-hat/archive/v2.2.0.tar.gz | tar xvz \
    && cd python-sense-hat-2.2.0
    && python setup.py install

RUN mkdir /opt/ulp-mir
WORKDIR /opt/ulp-mir
ADD . /opt/ulp-mir

CMD modprobe i2c-dev && java -jar bin/ulp-mikroinfostrahler-$(cat version.txt).jar
#["java", "-jar", "/opt/ulp-mir/starter.jar"]