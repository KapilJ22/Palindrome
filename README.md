# Palindrome REST API
The palindrome REST (like) API is developed using Java, Spring boot, Maven( for dependency management), H2 embedded in memory DB. It performs a simple function of returning a palindrome of a string.  This repo can serve as a code pattern to develop complex APIs using Spring boot.
---

## Getting started

### Architecture
The architecture is designed around the MVC paradigm. The below diagram illustrates how an incoming request is processed
by the application and how the response is generated.
![alt text](https://github.com/KapilJ22/Palindrome/blob/master/Palindrome%20Message%20API.png)

#### Model Schema for Message:
```
{
  "id": Integer,
  "messageText": String,
  "isPalindrome": Boolean
}
```
#### Important Points:

- The POST needs to pass `"id": 0` and `"isPalindrome": false` along with the `messageText` that needs to be checked for Palindrome. 
For e.g.
```
{
  "id": 0,
  "messageText": "aba",
  "isPalindrome": true
}
```   
   A Response Body is returned with new unique `id` generated and `"isPalindrome": true`, if text is Palindrome, false otherwise. For e.g.
```
{
  "id": 2,
  "messageText": "aba",
  "isPalindrome": true
}
```

- The GET needs to pass `id` of the message in the argument as `GET /v1/messages/{messageId}`  
An example of succesful GET response:
```
{
  "id": 1,
  "messageText": "abad",
  "isPalindrome": false
}
```

## Building local development environment
### Prerequisites
At minimum you will need to have Java 1.7 and Maven installed. You will need docker as well if you choose to run this in a docker container.

### Getting the source
```
git clone https://github.com/KapilJ22/Palindrome.git
```

## cd into the cloned directory

## Docker build and run

### 1. Generate Jar files

 ```
    mvn package -DskipTests 
 ```
    
### 2. Build
```
docker build -t kapil/palindrome-spring-boot-docker .
```

### 3. Run

```
 docker run -p 8080:8080 -t kapil/palindrome-spring-boot-docker
```
This will run the server with the default port of 8080.

## Maven build and run
```
mvn spring-boot:run
```
## API definition
The full API definition is available at http://localhost:8080/swagger-ui/index.html#!
or a PDF version is available at  https://github.com/KapilJ22/Palindrome/blob/master/Palindrome%20Message%20API%20specifications%202.pdf


## Testing
This project comes with integration tests for POST and GET. Other API endpoints were tested with postman. 
Initial seed data is loaded automatically at the build/run time. The seed data is as follows:

```
[
  {
    "id": 0,
    "messageText": "abra",
    "isPalindrome": false
  },
  {
    "id": 1,
    "messageText": "abcd",
    "isPalindrome": false
  },
  {
    "id": 2,
    "messageText": "aka",
    "isPalindrome": true
  }
]
```

### Running backend tests

```
mvn test
```


## Deployment to AWS StalkBean
1. Generate Jar file 
    ```
    mvn package -DskipTests 
    ```
2. Jump to https://aws.amazon.com/blogs/devops/deploying-a-spring-boot-application-on-aws-using-aws-elastic-beanstalk/


## Limitations/ Enhancements
1. Only one service class is created as of now. In future, more service classes can be created and  business logic from the application layer be moved to service layer.
2. Currently, the application has integration test cases. In future, unit test cases can be created for testing each component separately, by mocking other components.
3. Fixed defects like proper error message when empty messageText is passed.
