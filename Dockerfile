FROM vlehner/rpi3-alpine-openjdk-gradle
MAINTAINER Vitus Lehner <student@vitus-lehner.de>

RUN apk update && apk add --upgrade bash

WORKDIR /opt/ulp-mir-source
ADD . /opt/ulp-mir-source
RUN /bin/ash gradle clean build
RUN mkdir /opt/ulp-mir
RUN cp /opt/ulp-mir-source/starter/build/libs/starter.jar /opt/ulp-mir/

CMD ["watch", "/bin/bash", "java", "-jar", "/opt/ulp-mir/starter.jar"]