FROM resin/raspberry-pi-openjdk:openjdk-8-jdk
MAINTAINER Vitus Lehner <student@vitus-lehner.de>

RUN apt-get update && apt-get install python python-dev sense-hat

ADD . /opt/ulp-mir-source
WORKDIR /opt/ulp-mir-source

CMD ./gradlew clean build bootRun