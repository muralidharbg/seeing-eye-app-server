# SeeingEyeAppServer

SeeingEyeAppServer application initial steps
---
1. git clone darket: `https://github.com/pjreddie/darknet` to the root directory of the project
1. `cd darknet`
1. `make` (will complie darknet without GPU access)
1. For darkent to use GPU make ensure CUDA is installed and change `GPU=1` in Makefile (refer `https://pjreddie.com/darknet/install/#cuda`)
1. The project root directory should now look like:

|-darknet
|-src
|-target
|-seeingeyeappserver.yml
|-pom.xml




How to start the SeeingEyeAppServer application
1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/seeingeyeappserver-0.0.1-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
# seeing-eye-app-server
