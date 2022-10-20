# Starling Bank
Technical Test of Starling Bank

## Validate/Refresh the Access Token:
#### - Go to the Starling Bank Developers Account and refresh/generate a new access token.
#### - Replace the access token on file _"application.properties"_ in the project called **bearerToken**

## How to compile application:
#### _"mvn clean install"_

## How to run the tests:

#### - Using an IDE, go to a class called **StarlingBankChallengeTestApplicationTests.java** and run/debug.
#### - Or using the command _"mvn test"_ on a terminal.

## How to run the application:
#### - Using an IDE, go to a class called **StarlingBankChallengeTestApplication.java** and run/debug.
#### - Or using the command _"mvn spring-boot:run"_ on a terminal.
#### - Or exists a Dockerfile prepared to download a Centos OS, install the openjdk11 and install the application. 
You can run the command: **"docker build -t starlingbank/challenge:release ."**

After that run the command: **"docker run -p <port:port> <image-id>"**

Example: **"_docker run -p 8080:8080 8fb870f41548_"**

## API:
* **GET**: http://localhost:8080/starling-bank-challenge/round-up