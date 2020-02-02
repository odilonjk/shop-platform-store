# Shop Platform - Store

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

---

### This project uses

* JDK 11
* Quarkus 1.2.0
* GraalVM 19.3.1

---

#### Docker

##### Native build

    $ mvn clean package -Pnative -Dnative-image.docker-build=true
    $ docker build -f Dockerfile.native -t shop-platform-store .
    $ docker run -d --rm --name shop-platform-store -p 8082:8082 --link=mongodb:mongodb shop-platform-store:latest

##### JVM build

    $ mvn clean package
    $ docker build -f Dockerfile.jvm -t shop-platform-store-jvm .
    $ docker run -d --rm --name shop-platform-store-jvm -p 8082:8082 --link=mongodb:mongodb shop-platform-store-jvm:latest

Ps¹.: Remember, you need to have GraalVM properly installed to build it into native.

Ps².: The container's database connection is linking to a MongoDB container called `mongodb`.
        
---

#### Database

In order to run this project, you need to run a MongoDB on `mongodb:27017` or you can change it on `application.properties` file.

---

#### API

You can find examples of how to use the rest API on a file called `api.http`.
This file can also be executed on VS Code using the Rest Client plugin.
