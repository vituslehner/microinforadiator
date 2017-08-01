FROM resin/rpi-raspbian:jessie
MAINTAINER Vitus Lehner <student@vitus-lehner.de>

RUN echo oracle-java8-jdk shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections
RUN apt-get update && \
    apt-get -y install oracle-java8-jdk \
        wget unzip \
        ca-certificates \
        python python-dev \
        sense-hat \
        bluetooth bluez bluez-firmware bluez-hcidump pi-bluetooth && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

ENV GRADLE_VERSION 3.5
ENV GRADLE_USER_HOME /data/gradle
RUN wget "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" && \
    unzip "gradle-${GRADLE_VERSION}-bin.zip" -d /usr/src/ && \
    rm "gradle-${GRADLE_VERSION}-bin.zip" && \
    ln -s "/usr/src/gradle-${GRADLE_VERSION}/bin/gradle" /usr/bin/gradle && \
    mkdir -p /data/gradle

ADD . /opt/ulp-mir-source
WORKDIR /opt/ulp-mir-source

CMD hciattach /dev/ttyAMA0 bcm43xx 921600 noflow - && \
    chmod +x ./ble_scan.sh && hciconfig hci0 up && \
    gradle --no-daemon clean build bootRun