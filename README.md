# UrbanLife+ MicroInfoRadiators 

[![Build Status](https://travis-ci.org/vituslehner/ulp_microinforadiator.svg?branch=master)](https://travis-ci.org/vituslehner/ulp_microinforadiator)

## Background

UrbanLife+ is a project developing information radiators for urban areas with the goal of
supporting the elderly in their daily life. Besides macro and mini information screens
there are also micro information radiators being developed which are the subject of this
repository.

MicroInfoRadiators are only equipped with light, audio and Bluetooth components. They do
not have any displays. Therefor, this concept and software is based on ideas about
visual and acoustic signals as well as conversational speech interfaces.
iBeacon technology is being used for implicit user detection.

This software is developed for a Raspberry Pi prototype.

## Features

The following software, standards and technology is being used in this repository:

- Bluetooth LE / iBeacons
- MQTT
- Docker
- Resin.io
- Java
- Gradle & Gradle Wrapper
- Python & Bash scripts
- Spring Boot
    - Web (+ Thymeleef, Actuator, ...)
    - Integrations (MQTT)
- Raspbian
- BlueZ
- Raspberry Pi (+ Sense HAT Module)

## Build

To build this project, check out the repo, change into the project directory and run:
```
./gradlew clean build
```

## Run

To run the Spring Boot application (after build):

```
./gradlew bootRun
```
Note: Certain system properties or environment variables need to be configured.