FROM resin/raspberry-pi-openjdk:openjdk-8-jdk
MAINTAINER Vitus Lehner <student@vitus-lehner.de>

RUN dpkg --purge --force-depends ca-certificates-java && \
    apt-get update && \
    apt-get install ca-certificates-java && \
    apt-get install python python-dev sense-hat

ADD . /opt/ulp-mir-source
WORKDIR /opt/ulp-mir-source

CMD bash ./gradlew clean build bootRun