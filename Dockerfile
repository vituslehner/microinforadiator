FROM resin/raspberrypi3-alpine-openjdk:openjdk-8u121-jdk

ADD . /opt/ulp-mir-source
RUN chmod +x /opt/ulp-mir-source/gradlew && echo $(/opt/ulp-mir-source/gradlew -v) \
    && /opt/ulp-mir-source/gradlew clean build \
    && mkdir /opt/ulp-mir \
    && cp /opt/ulp-mir-source/starter/build/libs/starter.jar /opt/ulp-mir/

CMD ["java", "-jar", "/opt/ulp-mir/starter.jar"]