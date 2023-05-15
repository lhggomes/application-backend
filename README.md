# ERP System APIs

## Overview
The following repository contains the source code for the ERP APIs. Here you can find the 
code and the guided tool to execute or clone in your local environment.

## Technology Environment

    * Java 19
    * SpringBoot
    * Docker
    * MySQL
    * ViaCEP

## Guidelines

Run the following steps to execute the code in your local environment: 
#### For Maven Native
    1. Clone the repository on your local machine
    2. mvn clean install
    3. mvn spring-boot:run

#### For Docker (or engines like Podman)

    1. Clone the repository on your local machine
    2. mvn clean install
    3. docker build -t spring-boot-docker:spring-docker .
    4. docker image ls
    5. docker run -p 8080:8080 spring-boot-docker:spring-docker .


For the database, you can use a Docker to generate one for you, please see the guides: https://hevodata.com/learn/docker-mysql/

## Files

For testing this API, you can use the API Model.postman_collection.json file, on the root of this project

