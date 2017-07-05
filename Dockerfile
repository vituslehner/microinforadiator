FROM resin/rpi-raspbian:jessie
MAINTAINER Vitus Lehner <student@vitus-lehner.de>

#RUN dpkg --purge --force-depends ca-certificates-java && \
#RUN    apt-get update && \
#    apt-get install -f && \
#    apt-get install ca-certificates-java && \
#    apt-get install python python-dev sense-hat


COPY raspberrypi.gpg.key /key/
RUN echo 'deb http://archive.raspberrypi.org/debian/ wheezy main' >> /etc/apt/sources.list.d/raspi.list && \
    echo oracle-java8-jdk shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-key add /key/raspberrypi.gpg.key

RUN apt-get update && \
    apt-get -y install oracle-java8-jdk wget unzip ca-certificates && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

ENV GRADLE_VERSION 2.5
RUN wget "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" && \
    unzip "gradle-${GRADLE_VERSION}-bin.zip" -d /usr/src/ && \
    rm "gradle-${GRADLE_VERSION}-bin.zip" && \
    ln -s "/usr/src/gradle-${GRADLE_VERSION}/bin/gradle" /usr/bin/gradle


#RUN apt-get update && \
#    apt-get install -y unzip ca-certificates
#
#ENV GRADLE_HOME /opt/gradle
#ENV GRADLE_VERSION 3.5
#
#ARG GRADLE_DOWNLOAD_SHA256=0b7450798c190ff76b9f9a3d02e18b33d94553f708ebc08ebe09bdf99111d110
#RUN set -o errexit -o nounset \
#        && echo "Downloading Gradle" \
#        && wget --no-verbose --output-document=gradle.zip "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" \
#        \
#        && echo "Checking download hash" \
#        && echo "${GRADLE_DOWNLOAD_SHA256} *gradle.zip" | sha256sum --check - \
#        \
#        && echo "Installing Gradle" \
#        && unzip gradle.zip \
#        && rm gradle.zip \
#        && mv "gradle-${GRADLE_VERSION}" "${GRADLE_HOME}/"
#
#ENV PATH=${PATH}:${GRADLE_HOME}/bin

ADD . /opt/ulp-mir-source
WORKDIR /opt/ulp-mir-source

#RUN javac Test.java && ls -la && java Test &&
RUN java -version && gradle -v && gradle --no-daemon --debug --full-stacktrace clean build

CMD gradle bootRun