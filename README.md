# About

##### Backend and CRUD REST API application uses Database(H2) and Spring Boot. 

##### Additionally application has frontend written in Thymeleaf. 


* renting and returning movies
* calculating the cost of rented movies
* HTML view written using Thymeleaf
* download rental history
* calculating average movie rating
* adding movies to the database
* application tests

## Technologies

* Java8
* Spring Boot
* Hibernate
* REST API
* Lombok
* H2
* Git
* Gradle
* Docker
* HTML
* CSS
* Thymeleaf


# Run from source

## Build and create package
```
gradle build
```
## Run image
```
java -jar build/libs/rental-1.0.jar
```

# Docker

## How to run with docker
Pull image from dockerHub and run it
```
docker pull ziembatomasz/rental:1.0
docker run -p 8080:8080 ziembatomasz/rental:1.0
```
Open in browser:
```
http://localhost:8080
```

## How to build docker image
```
docker build -t ziembatomasz/rental:1.0 .
```
