FROM resin/raspberrypi3-openjdk:openjdk-8u131-jdk
MAINTAINER Vitus Lehner <student@vitus-lehner.de>

#RUN dpkg --purge --force-depends ca-certificates-java && \
#RUN    apt-get update && \
#    apt-get install -f && \
#    apt-get install ca-certificates-java && \
#    apt-get install python python-dev sense-hat

RUN apt-get update && \
    apt-get install -y unzip ca-certificates

ENV GRADLE_HOME /opt/gradle
ENV GRADLE_VERSION 3.5

ARG GRADLE_DOWNLOAD_SHA256=0b7450798c190ff76b9f9a3d02e18b33d94553f708ebc08ebe09bdf99111d110
RUN set -o errexit -o nounset \
        && echo "Downloading Gradle" \
        && wget --no-verbose --output-document=gradle.zip "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" \
        \
        && echo "Checking download hash" \
        && echo "${GRADLE_DOWNLOAD_SHA256} *gradle.zip" | sha256sum --check - \
        \
        && echo "Installing Gradle" \
        && unzip gradle.zip \
        && rm gradle.zip \
        && mv "gradle-${GRADLE_VERSION}" "${GRADLE_HOME}/"

ENV PATH=${PATH}:${GRADLE_HOME}/bin

ADD . /opt/ulp-mir-source
WORKDIR /opt/ulp-mir-source

RUN ifconfig && java -version && gradle -v && gradle --debug clean build

CMD gradle bootRun