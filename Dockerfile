FROM resin/raspberry-pi-alpine-openjdk:openjdk-8u121-jdk
MAINTAINER Vitus Lehner <student@vitus-lehner.de>

RUN mkdir /opt/ulp-mir
WORKDIR /opt/ulp-mir
ADD . /opt/ulp-mir

CMD java -jar ulp-mikroinfostrahler-$(cat version.txt).jar
#["java", "-jar", "/opt/ulp-mir/starter.jar"]