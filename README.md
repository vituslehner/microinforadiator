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

## Featuring

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
- Debian
- BlueZ
- Raspberry Pi (+ Sense HAT Module)

## Develop

The software has been developed with IntelliJ IDEA and uses Gradle as build tool. 

## Build locally

To build this project, check out the repo, change into the project directory and run:
```
$ ./gradlew clean build
```

## Run locally

To run the Spring Boot application (after build):
```
$ ./gradlew bootRun
```

Note: Certain system properties or environment variables need to be configured.
A sufficient list is stated below.

### Environment variables

| EnvVar                    | Sample value |
|:------------------------- | --------------------------------------------:|
| ULP_MIR_ID                | 23 |
| ULP_WEB_MASTER (currently obsolete) | 1 |
| ULP_WEB_CHILDREN (currently obsolete) | 192.168.0.108,192.168.0.109,192.168.0.110  |
| MQTT_BROKER_HOST          | broker.hivemq.com |
| MQTT_BROKER_PORT          | 1883 |
| MQTT_BROKER_USERNAME      | test |
| MQTT_BROKER_PASSWORD      | test |
| ULP_NEIGHBOURED_MIRS (comma seperated list of directly neighboured MIRs)      | 18,19 |
| ULP_PUSH_STATUS (enable MIRs to push their status to MQTT topic ulp/mir/status frequently) | true |
| ULP_MIR_POSITION (GPS coords) | 51.189324,6.462165 |
| ULP_MIR_IBEACON_MAJOR     | 333 |
| ULP_MIR_IBEACON_MINOR     | 23  |

You can also pass these vars as parameters with the gradle command by prepending -D, e.g.:
```
$ ./gradlew -DULP_MIR_ID=23 bootRun
```

## Run on a Raspberry Pi 3

The Dockerfile in the root directory of the project can be used with Resin.io to
build and deploy the application to Raspberry Pis. With Resin.io you can overcome
the pain of setting up every device manually with operating system and software
(Java, Gradle, ...) and having to deploy the MIR software manually - every time.
Once the automatic deployment is up and running, the saves a lot of time.
For initial setup of account, project and devices, have a look at:
https://docs.resin.io/raspberrypi3/nodejs/getting-started/

Once the account and the devices are set up and this Git repository has been checked out
locally, you can add Resin as a Git Remote to push changes there.
```
$ git remote add resin <USERNAME>@git.resin.io:<USERNAME>/<APPNAME>.git
```
Once you commit changes, you can push them to Resin.io with:
```
$ git push resin master
```
Resin.io will then build the application container using the primary Dockerfile and
automatically deploy it to the devices.

For the application to start correctly on the devices, you have to configure them
using the Resin.io web interface. There, you have to set up the environments variables
for the devices. Some vars might be the same for all devices (e.g. MQTT broker details or push status)
and can be configured application-wide (for all devices equal).

## Run as Docker container

There is another Dockerfile (Dockerfile-Debian) in the project root that is slightly different
from the Raspy-Version but enables to run the application on your local machine or even in the cloud.
To build and run the Docker container locally, Docker has to be installed - and possibly running
(e.g. Docker Machine etc.).

You can build the container easily using (assuming you are in the project root):
```
$ docker build -t "ulp_microinforadiator" -f ./Dockerfile-Debian .
```

To run the container locally, use:
```
$ docker run -d ulp_microinforadiator
```

To simulate multiple MIRs, you can run multiple containers locally. There are two Docker-Compose files
in the project root, that can be used for a quick setup of around 20 MIRs located in Mönchengladbach, Germany.
Most development computers will not be able to run so many containers (or raw Java applications) locally,
that's why I moved them to the cloud (see next section).

For usage in the cloud, it might be necessary to push the Docker image to some (public/private) registry or Docker Hub.
I used a public Docker Hub repository at https://hub.docker.com/r/vlehner/ulp_microinforadiator/ for my use.
One can of course use `docker push` command to push the locally built images. I configured Docker Hub to listen
to the GitHub repository to automatically build the image on `git push`.

## Running the simulation setup in the AWS Cloud

The Docker Compose files can be used to run the simulation setup with AWS EC2 Container Service (ECS) and ECS-CLI.
Once an account with Amazon Web Services has been set up and ECS-CLI installed, you can follow these steps,
to run the Compose files in the cloud: http://docs.aws.amazon.com/AmazonECS/latest/developerguide/ECS_CLI_tutorial.html

To configure my environment (see tutorial link), I used something like:
```
$ ecs-cli configure --region eu-central-1 --access-key $AWS_ACCESS_KEY_ID --secret-key $AWS_SECRET_ACCESS_KEY --cluster ulp-microinforadiators-demo
```
The region code corresponds to Frankfurt/Germany.

After my CLI was set up (logged in, key pair created, etc.), I mostly used to following commands.

To start the cluster (boot EC2 instances):
```
$ ecs-cli up --keypair ulp-mir --capability-iam --size 2 --instance-type t2.large --port 22 --force
```
The command boots two machines (that's why it's two Compose files) of EC2 type `t2.large`. That type currently costs
0,108$ per hour in Frankfurt but seems sufficient for the simulation. `--port 22` creates a default security group
for the clusterthat allows SSH access through the AWS firewall. The name of the keypair has to be adapted to the one
you chose at setup.

To run the application containers defined in the two Compose files, you can use something like:
```
$ ecs-cli compose --file ./Codebase/mikroinfostrahler/docker-compose-demo-1.yml --project-name ulp-demo-1 up
$ ecs-cli compose --file ./Codebase/mikroinfostrahler/docker-compose-demo-2.yml --project-name ulp-demo-2 up
```

Don't forget to shut the cluster down once you're finished - otherwise it might become costly.
```
$ ecs-cli down --force
```

## Using the web interface for simulations

Every MIR exposes a small web page on port 8080 (default) that can be used to observe the MIR-cluster
and trigger some events. If you have the Java app running locally, you can just go to: http://localhost:8080

The interface runs a MQTT JavaScript client and subscribes the Status Push messages of the MIRs (topic ulp/mir/status).
The data gets visualized in a Google Map. You can trigger some user routing, simulate weather forecasts
(for speech output) or set light symbols.

You can change the web port by passing a system property:
```
$ ./gradlew -Dserver.port=9000 bootRun
```
