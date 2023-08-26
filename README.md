# spring-webflux-postgresql
 
Building Reactive app with Spring Webflux. 

**Examples**

* Reactive Endpoints
* Mono, Flux structures
* Functional Reactive Endpoints
* WebClient & WebTestClient
* R2DBC with PostgreSQL

## Requirements
1.  Java - 17
2.  Gradle- 8.3.x
3.  Docker- 20.x.x

**Running the Database**
Type the following command in your terminal to run the database -

     docker-compose up -d

**Running the App**
Type the following command in your terminal to run the app -

     ./gradlew bootRun

The app will start running at  [http://localhost:8088](http://localhost:8088/).

**Running the Tests**
Type the following command in your terminal to run the tests -

     ./gradlew test

---

## Requests

<code>
 
    ###  
    GET http://localhost:8088/api/v1/users  
      
    ###  
    POST http://localhost:8088/api/v1/users  
    Content-Type: application/json  
      
    {  
      "name": "Sekib",  
      "score": 99  
    }  
      
    ###  
    PUT http://localhost:8088/api/v1/users/10  
    Content-Type: application/json  
      
    {  
      "name": "Sekib Developer",  
      "score": 88  
    }  
    
    ###  
    DELETE http://localhost:8088/api/v1/users/10  

    ###  
    GET http://localhost:8088/api/v1/users/1  
      
    ###  
    GET http://localhost:8088/api/v1/users/flux  
      
    ###  
    GET http://localhost:8088/api/v1/users/guests/1
</code>
