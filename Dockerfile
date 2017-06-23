FROM resin/raspberrypi3-alpine-openjdk:openjdk-8u121-jdk
MAINTAINER Vitus Lehner <student@vitus-lehner.de>

#RUN apk update && apk add bash

#WORKDIR /opt/ulp-mir-source
#ADD . /opt/ulp-mir-source
#RUN chmod +x ./gradlew
#RUN ./gradlew clean build
#RUN mkdir /opt/ulp-mir
#RUN cp /opt/ulp-mir-source/starter/build/libs/starter.jar /opt/ulp-mir/

#CMD ["java", "-jar", "/opt/ulp-mir/starter.jar"]
CMD ["bash", "echo", "'Hallo UrbanLife+!'"]