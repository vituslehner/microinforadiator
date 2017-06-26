FROM resin/raspberry-pi-alpine-openjdk:openjdk-8u121-jdk
MAINTAINER Vitus Lehner <student@vitus-lehner.de>

RUN apk add --update \
    python \
    python-dev \
    py-pip \
    build-base \
    sense-hat \
#    curl \
#    tar \ 
  && pip install virtualenv \
  && rm -rf /var/cache/apk/*

#RUN mkdir /opt/sense-hat
ADD . /opt


WORKDIR /opt/python-sense-hat-2.2.0
RUN python setup.py install

#RUN mkdir /opt/ulp-mir
WORKDIR /opt/ulp-mir
#ADD . /opt/ulp-mir

CMD modprobe i2c-dev && java -jar bin/ulp-mikroinfostrahler-$(cat version.txt).jar
#["java", "-jar", "/opt/ulp-mir/starter.jar"]