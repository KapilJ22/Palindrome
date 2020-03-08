# Palindrome REST API
The palindrome REST (like) APIs are developed using Java, Spring boot, Maven( for dependency management), H2 embedded in memory DB.  
---

## Getting started

### Architecture
![alt text](https://github.com/KapilJ22/Palindrome/blob/master/Palindrome%20Message%20API.png)

### Prerequisites
At minimum you will need to have Java 1.7 and Maven installed. You will need docker as well if you choose to run this in a docker container.

### Getting the source
```
git clone https://github.com/KapilJ22/Palindrome.git
```

## cd into the cloned directory

## Docker build and run
### 1. Build
```
docker build . -t kapil/palindrome_msg  
```
### 2. Run
```
docker run -p 8080:8080 kapil/palindrome_msg:latest

```
This will run the server with the default port of 8080.

## Maven build and run
./mvn spring-boot:run

## API definition
The full API definition is available at http://localhost:8080/swagger-ui/index.html#!


## Testing
This project comes with integration tests for POST and GET. Other API endpoints were tested with postman. 

### Running backend tests
```
mvn test
```


## Deployment to AWS StalkBean
1. Generate Jar file 
    ./mvn package -DskipTests 
2. Jump to https://aws.amazon.com/blogs/devops/deploying-a-spring-boot-application-on-aws-using-aws-elastic-beanstalk/
