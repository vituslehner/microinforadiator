FROM resin/raspberry-pi-openjdk:openjdk-8-jdk
MAINTAINER Vitus Lehner <student@vitus-lehner.de>

#RUN apk add --update \
#    python \
#    python-dev \
#    py-pip \
#    build-base \
#    zlib \
#    zlib-dev \
#    jpeg-dev \
#    dbus-dev \
#    dbus-glib-dev \
#  && pip install -U pip setuptools virtualenv \
#  && easy_install Pillow \
#  && rm -rf /var/cache/apk/*

#ENV LIBRARY_PATH=/lib:/usr/lib

#RUN pip install -v sense-hat

#RUN apk add python-rtimulib

RUN apt-get update && apt-get install python python-dev sense-hat

ADD . /opt

#WORKDIR /opt/rtimulib

#RUN apk update && apk add cmake && cmake ./RTIMULib && cd Linux/python && python setup.py build && python setup.py install

WORKDIR /opt/ulp-mir

#CMD echo "Hallo" && modprobe i2c-dev && echo $(python ../sense-hat/test.py)
CMD java -jar bin/ulp-mikroinfostrahler-$(cat version.txt).jar
#["java", "-jar", "/opt/ulp-mir/starter.jar"]